package com.ssafy.ws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.UserService;
import com.ssafy.ws.util.JwtUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;
	private JwtUtil jwtUtil;
	
	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}

	/**
	 *  로그인 : 인증성공 시 JWT 생성 (id 저장)
	 * @param id
	 * @param pass
	 * @return success | failed  success일 때 header : access-token 저장
	 */
	 @PostMapping("/login")
	 public ResponseEntity<String> login(String id, String pass) {
		 User user = userService.signIn(id, pass);
		 
		 if(user != null) {
			 //token생성 - access-token 응답 헤더에 setting
			 String accessToken = jwtUtil.createToken(id);
			 return ResponseEntity.status(HttpStatus.OK).header("access-token", accessToken).body("success");
		 }else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("failed"); 
		 }
	 }

	 
	 @PostMapping("/")
	 public ResponseEntity<String> save(@RequestBody User user) {
		 userService.signUp(user);
		 return ResponseEntity.status(HttpStatus.CREATED).body("success");
	 }
	 
	 
	 /**
	  * Spring에서 @ExceptionHandler를 사용하여 예외를 처리할 때, 예외 타입별로 다른 메시지를 전달
	  * 컨트롤러 클래스에서 예외 처리
	  * Global전역처리는 ControllerAdvice(RestControllerAdvice) class로 처리
	  * @param model
	  * @return
	  */
//	 @ExceptionHandler(NoSuchElementException.class)
//	 public String hadleAuthenticationError(Exception error, Model model) {
//		 model.addAttribute("mgs",error.getMessage());
//		 return "error/commonerr";
//	 }
}






