package com.ssafy.ws.controller;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/users")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	/**
	 * <pre>
	 * /login 요청이 post 방식으로 왔을 때 login 처리한다.
	 * </pre>
	 * 
	 * 사용자의 요청에서 계정 정보를 추출해 로그인 처리한다.
	 * 일단 사용자 id가 ssafy, 비밀번호가 1234면 로그인에 성공으로 간주한다.
	 * 로그인 성공 시 session에 정보를 담고 redirect로 index로 이동한다.
	 * 그렇지 않을 경우는 로그인 실패 메시지를 model에 담고 forward로 index로 이동한다.
	 * 
	 * @param user
	 *            전달된 파라미터는 ModelAttribute를 통해서 객체로 받을 수 있다.
	 * @param session
	 *            사용자 정보를 세션에 저장하기 위해 사용한다.
	 * @param model
	 *            Request scope에 정보를 저장하기 위해서 사용된다.
	 * @return
	 */
	 @PostMapping("/login")
	 public String login(String id, String pass, HttpSession session, Model model) {
		 User user = userService.signIn(id, pass);
		 
		 if(user != null) 
			 session.setAttribute("loginUser", user); 
		 return "index";
	 }

	/**
	 * <pre>/logout을 get 방식으로 요청했을 때 session을 만료 시키고 로그아웃 처리한다.</pre>
	 * 다음 경로는 redirect 형태로 /index로 이동한다.
	 * @param session
	 * @return
	 */
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
		 if(session != null ) session.invalidate();
	 	return "index";
	 }
	 
	 /**
	  * 회원 가입
	  */
	 @GetMapping("/regist")
	 public String registForm() {
		 return "signup";
	 }
	 
	 @PostMapping("/regist")
	 public String save(User user) {
		 userService.signUp(user);
		 return "index";
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






