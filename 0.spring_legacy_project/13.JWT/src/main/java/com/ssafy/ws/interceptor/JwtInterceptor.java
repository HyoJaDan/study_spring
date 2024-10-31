package com.ssafy.ws.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.ws.exception.UnAuthorizedException;
import com.ssafy.ws.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtil jwtUtil;
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader("access-token");
		String method = request.getMethod();
		if(method.equalsIgnoreCase("GET"))return true;
		if( token != null) {
				if( jwtUtil.validate(token) != null ) {
					log.info("valid user");
					return true;
				}
			}
		log.info("invalid user");
		throw new UnAuthorizedException();
	}

	
}
