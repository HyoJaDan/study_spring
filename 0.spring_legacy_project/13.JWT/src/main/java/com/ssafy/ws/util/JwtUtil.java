package com.ssafy.ws.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
//	Environment env;
//
//	public JwtUtil(Environment env) {
//		this.env = env;
//	}

//	private String SALT = env.getProperty("jwt.salt");
	private String SALT = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8));
//    private long EXPIRE_TIME = Long.parseLong(env.getProperty("jwt.expire-time"));
	private long EXPIRE_TIME = 3600000;
    /**
     * Token 생성
     * paylod : { "userId":userId }
     * @param userId
     * @return
     */
	public String createToken(String userId) {

		Date exp = new Date(System.currentTimeMillis() +EXPIRE_TIME); // 1시간
		return Jwts.builder()
				.header()
				.add("type", "JWT")
				.and()
				.claim("userId", userId)
				.expiration(exp).signWith(secretKey)
				.compact();
	}

	/**
	 * Token 유효성 check
	 * @param token : request.getHeader("access-token") 
	 * @return
	 */
	public Jws<Claims> validate(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token);
	}
}
