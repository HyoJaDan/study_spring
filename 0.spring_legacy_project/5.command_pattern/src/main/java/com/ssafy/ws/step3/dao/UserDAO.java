package com.ssafy.ws.step3.dao;

import com.ssafy.ws.step3.dto.User;

public interface UserDAO {
	public void createUser(User user);
	public User findById(String id);
	
}
