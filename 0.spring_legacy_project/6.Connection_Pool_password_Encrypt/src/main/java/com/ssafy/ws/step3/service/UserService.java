package com.ssafy.ws.step3.service;

import java.util.NoSuchElementException;

import com.ssafy.ws.step3.dto.User;

public interface UserService {
	public void signUp(User user);
	public User signIn(String id, String pass) 
			throws NoSuchElementException;
}
