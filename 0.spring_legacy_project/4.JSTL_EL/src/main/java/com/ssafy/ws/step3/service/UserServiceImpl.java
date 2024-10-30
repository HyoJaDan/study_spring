package com.ssafy.ws.step3.service;

import java.util.NoSuchElementException;

import com.ssafy.ws.step3.dao.UserDAO;
import com.ssafy.ws.step3.dao.UserDAOImpl;
import com.ssafy.ws.step3.dto.User;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	private static UserService userService = new UserServiceImpl();
	
	private UserServiceImpl() {
		userDAO = UserDAOImpl.getInstance();
	}
	
	public static UserService getInstance() { return userService;}

	@Override
	public void signUp(User user) {
		userDAO.createUser(user);

	}

	@Override
	public User signIn(String id, String pass) 
			throws NoSuchElementException {
		User user = userDAO.findById(id);
		if(user == null || !user.getPass().equals(pass) ) 
			throw new NoSuchElementException();
	    return user;
	}

}
