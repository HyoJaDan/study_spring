package com.ssafy.ws.step3.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import com.ssafy.ws.step3.dto.User;
import com.ssafy.ws.step3.service.BookServiceImpl;
import com.ssafy.ws.step3.service.UserService;
import com.ssafy.ws.step3.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController implements Controller {
    private final UserService userService;

    public UserController() {
    	userService = UserServiceImpl.getInstance();
	}
    
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String nextPage="/index.jsp";

		try {
			Class userClass= Class.forName("com.ssafy.ws.step3.controller.UserController");
			UserController userObject = (UserController)userClass.getDeclaredConstructor().newInstance();
			Class [] parameterTypes = {jakarta.servlet.http.HttpServletRequest.class,
					jakarta.servlet.http.HttpServletResponse.class};
			Object [] parameters = {request, response};

			Method m= userClass.getDeclaredMethod(action, parameterTypes);
		
			 
			 nextPage=  (String) m.invoke(userObject, parameters);
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nextPage;
	}


	private String signup(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException{
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		 userService.signUp(User.builder()
							.id(id)
							.name(name)
							.pass(pass)
							.build());
		
		return "/index.jsp";
	}
	
	private String signin(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException{
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		User user= userService.signIn(id, pass);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			return "/index.jsp";
		}else {
			request.setAttribute("msg", "아이디 암호 확인하세요.");
			return "/error/error.jsp";
		}
	}
	

	private String signout(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session != null && !session.isNew())
			session.invalidate();
		
		return "/index.jsp";
	}

	private String signupForm(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException {
		return "/signup.jsp";
	}
}
