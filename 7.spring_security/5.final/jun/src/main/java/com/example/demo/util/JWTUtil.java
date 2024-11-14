package com.example.demo.util;

import com.example.demo.domain.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey secretKey;
    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }
    public String getCategory(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }
    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public Role getRole(String token) {
        try {
            String roleString = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("role", String.class);

            return Role.valueOf(roleString);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }


    // 토큰에서 만료여부 검증,추출하는 메서드
    public Boolean isExpired(String token) {
        try {
            // jwt 파싱해서 secretKey로 서명검증 및 payload에서 현재시간 기준 만료여부 추출
            return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // 만료된 경우 true 반환
        }
    }
    // 토큰 생성 메서드
    // category, username, role, 토큰유효시간 을 입력받아 JWT 토큰을 생성해 String 형태로 반환
    public String createJwt(String category, String username, Role role, Long expiredMs) {
        return Jwts.builder()
                .claim("category", category) // access, refresh 판단용
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis())) // 토큰 발행 시각
                .expiration(new Date(System.currentTimeMillis() + expiredMs)) // 토큰 만료 시각 (발생시각 + 유효시간)
                .signWith(secretKey)
                .compact();
    }

}