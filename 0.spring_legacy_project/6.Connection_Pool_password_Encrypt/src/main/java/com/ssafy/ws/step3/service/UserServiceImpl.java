package com.ssafy.ws.step3.service;

import java.util.NoSuchElementException;

import org.mindrot.jbcrypt.BCrypt;

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
		//회원가입  시 : password 암호화
		user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()) );
		userDAO.createUser(user);

	}

	@Override
	public User signIn(String id, String pass) 
			throws NoSuchElementException {
		User user = userDAO.findById(id);
		//로그인 시 : DB의 암호화된 password와 입력받은 평문 pass 비교
		if (!BCrypt.checkpw(pass, user.getPass()))
			throw new NoSuchElementException();
	    return user;
	}

}
