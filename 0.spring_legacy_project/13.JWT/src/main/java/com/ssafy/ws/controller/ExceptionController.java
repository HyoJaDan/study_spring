package com.ssafy.ws.controller;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ssafy.ws.exception.UnAuthorizedException;

/**
 * 전역 레벨에서 서버 오류를 처리하기 위해 @ControllerAdvice를 사용한다.
 */
@RestControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	/**
	 * 모든 예외를 rough하게 처리하기 위해 Exception 클래스를 처리하도록 설계한다.
	 * 해커 공격의 빌미를 제공할 수 있음으로 서버에서 발생한 구체적인 오류를 표시하지는 않는다.
	 * 하지만 사용자에게 알려줘야할 내용이 있다면 표시해주어야 한다.
	 * @param e
	 * @return
	 */
	 @ExceptionHandler(UnAuthorizedException.class)
	 public ResponseEntity<String> hadleAuthenticationError(UnAuthorizedException error) {
		 logger.error(error.toString());
		 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error.getMessage());
	 }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		logger.error("Exception 발생 : {}", ex.getMessage());
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
	

}
