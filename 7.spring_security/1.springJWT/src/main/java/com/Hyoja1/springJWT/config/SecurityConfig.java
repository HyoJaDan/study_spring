package com.Hyoja1.springJWT.config;

import com.Hyoja1.springJWT.domain.User;
import com.Hyoja1.springJWT.jwt.JWTFilter;
import com.Hyoja1.springJWT.jwt.JWTUtil;
import com.Hyoja1.springJWT.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // csrf
        // disable session 방식에서는 session이 계속 고정되기 때문에 방어를 해줘야 한다.
        // 근데 우리 jwt 방식은 stateless 상태로 관리하기 때문에 csrf에 대한 공격을 방어하지 않아도 되서 기본적으로
        // disabled 상태로 둔다.
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //controller별 경로에 대해서 어떤 권한을 가져야 되는지 인가 작업을 한다.
        // login, /, join 경로에 대해서는 모든 권한을 허용하고
        // admin 경로는 admin이라는 권한을 가진 사용자만 접근할수 있고
        // 그 외의 경로에 대해서는 로그인을 해야지 접근할수 있다.
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        // LoginFilter 앞에 JWTFilter 등록
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        // userNameAuthenticationFilter을 대체하는 필터를 추가하는 것이기 때문에 addFilterAt을 사용한다.
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //세션 설정. 세션은 항상 stateless로 설정해줘야 한다.
                http
                        .sessionManagement((session) -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}
