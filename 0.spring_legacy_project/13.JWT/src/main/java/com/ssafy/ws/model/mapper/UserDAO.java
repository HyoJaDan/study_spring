package com.ssafy.ws.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.model.dto.User;

@Mapper
public interface UserDAO {
	public void createUser(User user);
	public User findById(String id);
	
}
