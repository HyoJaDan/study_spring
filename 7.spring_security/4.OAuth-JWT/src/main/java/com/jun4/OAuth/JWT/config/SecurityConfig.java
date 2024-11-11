package com.jun4.OAuth.JWT.config;

import com.jun4.OAuth.JWT.jwt.JWTUtil;
import com.jun4.OAuth.JWT.oauth2.CustomSuccessHandler;
import com.jun4.OAuth.JWT.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // cors 설정
        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        // 허용할 출처(Origin) 설정 - 여기서는 http://localhost:3000 만 허용
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));

                        // 허용할 HTTP 메서드 설정 - 여기서는 모든 메서드(GET, POST, PUT, DELETE 등)를 허용
                        configuration.setAllowedMethods(Collections.singletonList("*"));

                        // 자격 증명(쿠키, Authorization 헤더 등)을 포함한 요청을 허용할지 설정
                        configuration.setAllowCredentials(true);

                        // 허용할 HTTP 헤더 설정 - 여기서는 모든 헤더를 허용
                        configuration.setAllowedHeaders(Collections.singletonList("*"));

                        // 3600초(1시간) 동안 캐시
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        // 클라이언트에게 노출할 헤더 설정 - Authorization 헤더를 노출하여 클라이언트가 접근할 수 있게 설정
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));
        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //oauth2
//        http
//                .oauth2Login(Customizer.withDefaults());
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated());

        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}