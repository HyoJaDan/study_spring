package com.OAuthSession3.OAuthSession.config;

import com.OAuthSession3.OAuthSession.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. 개발 환경이기 때문에 CSRF를 잠깐 끈다.
        http
                .csrf((csrf) -> csrf.disable());

        // 2. form 로그인 방식 끈다.
        http
                .formLogin((login) -> login.disable());

        // 3. httpBasic 방식 disable
        http
                .httpBasic((basic) -> basic.disable());

        // 4. 삭제
//        http
//                .oauth2Login(Customizer.withDefaults());
        http
                .oauth2Login((oauth2) -> oauth2
                        // 커스텀 로그인 하고 싶으면 컨트롤러 경로 등록
                        .loginPage("/login")
                        // userInfoEndPoint : 데이터를 받을수 있는 userDetails를 등록하는 userEndPoint이다.
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)));
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}