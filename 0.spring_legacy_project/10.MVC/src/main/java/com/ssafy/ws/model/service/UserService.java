package com.ssafy.ws.model.service;

import java.util.NoSuchElementException;

import com.ssafy.ws.model.dto.User;


public interface UserService {
	public void signUp(User user);
	public User signIn(String id, String pass) 
			throws NoSuchElementException;
}
