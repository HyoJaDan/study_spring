package com.ssafy.ws.model.dao;

import com.ssafy.ws.model.dto.User;

public interface UserDAO {
	public void createUser(User user);
	public User findById(String id);
	
}
