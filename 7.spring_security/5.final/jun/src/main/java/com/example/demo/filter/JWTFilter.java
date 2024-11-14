package com.example.demo.filter;

import com.example.demo.domain.Role;
import com.example.demo.domain.UserEntity;
import com.example.demo.details.JWTUserDetails;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access");

        // 토큰이 존재 여부 확인
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 여부 확인
        if (jwtUtil.isExpired(accessToken)) {
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // access 토큰 여부 확인
        String category = jwtUtil.getCategory(accessToken);
        if (!category.equals("access")) {
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            return;
        }

        // 토큰에서 username 과 role 추출
        String username = jwtUtil.getUsername(accessToken);
        Role role = jwtUtil.getRole(accessToken);

        // 추출한 정보로 userEntity 생성
        UserEntity user = UserEntity.localUserBuilder()
                .username(username)
                .password("temppassword")
                .role(role)
                .build();


        // UserDetails 에 userEntity 담기
        JWTUserDetails customUserDetails = new JWTUserDetails(user);

        // 시큐리티 인증 토큰 객체 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        // 세션에 사용자 등록 (임시 저장)
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 다음 필터로 요청, 응답 전달
        filterChain.doFilter(request, response);
    }
}
