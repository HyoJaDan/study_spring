package com.example.springjwt.jwt;

import com.example.springjwt.dto.CustomUserDetails;
import com.example.springjwt.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request 헤더에서 access 토큰 추출
        String accessToken = request.getHeader("access");

        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {
            filterChain.doFilter(request, response); // 다음 필터로 요청, 응답 전달
            return; // 메서드 종료 (필수)
        }

        // 토큰 만료 여부 확인, 만료 시 다음 필터로 넘기지 않음
        if (jwtUtil.isExpired(accessToken)) { // 만료 되었다면
            // response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            // response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return; // 메서드 종료 (필수)
        }

        // access 토큰 여부 확인 : "access" 헤더에서 추출한 토큰이 실제로 "access" 카테고리를 갖고 있는지 확인하여, 잘못된 토큰 사용을 방지
        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals("access")) {
            // response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            // response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return; // 메서드 종료 (필수)
        }

        // 토큰에서 username 과 role 추출
        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        // 추출한 정보로 userEntity 생성
        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password("temppassword") // 토큰에 비밀번호는 포함되지 않는다. 비밀번호 검증이 필요없다. 우리가 임의로 설정해주면 된다.
                .role(role)
                .build();

        // UserDetails 에 userEntity 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        // 시큐리티 인증 토큰 객체 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // 세션에 사용자 등록 (임시 저장)
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 필터로 요청, 응답 전달
        filterChain.doFilter(request, response);

    }
}