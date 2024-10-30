package com.ssafy.ws.model.service;

import java.util.NoSuchElementException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.UserDAO;
import com.ssafy.ws.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void signUp(User user) {
		//회원가입  시 : password 암호화
		user.setPass(BCrypt.hashpw(user.getPass(), BCrypt.gensalt()) );
		userDAO.createUser(user);

	}

	@Override
	public User signIn(String id, String pass){
		User user = userDAO.findById(id);
		//로그인 시 : DB의 암호화된 password와 입력받은 평문 pass 비교
		if (user!=null && BCrypt.checkpw(pass, user.getPass()))
			return user;
		else throw new NoSuchElementException("아이디 비밀번호 확인해주세요.");
	}

}
