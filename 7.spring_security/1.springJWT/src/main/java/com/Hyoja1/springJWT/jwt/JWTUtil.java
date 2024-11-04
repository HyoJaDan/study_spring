package com.Hyoja1.springJWT.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private SecretKey secretKey;

    /**
     * 1. application.properties의 spring.jwt.secret에 @Value로 접근한다.
     * 여기서 가져오는 값은 String인데 이걸 위의 변수처럼 객체로 바꾸는 과정이다.
     */
    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /**
     * 2.아래는 검증할 3개의 메서드이다. 각각의 메서드는 token을
     * 전달받아서 내부의 jwt파서를 이용해서 내부 데이터를 확인한다.
     * getUsername : username을 검증
     * getRole : Role 값을 검증
     * isExpired : token이 완료되었는지 확인
     */
    public String getUsername(String token) {
        // 파서를 이용해서 암호화가 된 토큰을 verifyWith을 통해 내가 가지고 있는 secretKey가 우리 서버에서 생성되었는지(대칭키니까) 그리고 그 키가
        // secretkey와 맞는지 확인한다. 이걸 builder type으로 return한다음에 claim을 확인하고, payload 부분에서 특정한 데이터를 get을 통해서 가져온다.
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    /**
     * 3. 토큰을 생성하는 메서드이다. 로그인이 완료되었을때, 성공했을때, serverSuccessfulHandler을 통해서
     * username, role, expiredMS를 받아서 생성한다.
     * 반환값으로 jwt.builder을 통해 토큰을 만든다. claim이라 선언한 뒤에 데이터를 넣어줄수 있다. (payload 부분)
     * issuedAt : 토큰 발행시간
     * expiration : 토큰 완료시간
     * signWith : Signature
     */
    public String createJwt(String username, String role, Long expiredMs) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
