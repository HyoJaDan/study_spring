package com.ssafy.ws.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.util.DBUtil;


@Repository
public class UserDAOImpl implements UserDAO {
	private DBUtil db;
	
	@Autowired
	public UserDAOImpl(DBUtil db) {
		this.db = db;
	}

	@Override
	public void createUser(User user) {
		Connection con=null;
		PreparedStatement stmt=null;
		String sql = new StringBuilder()
					.append("INSERT INTO user (id, name, pass)\n")
					.append("VALUES(?,?,?)")
					.toString();
		
		try {
			con= db.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1,  user.getId());
			stmt.setString(2,  user.getName());
			stmt.setString(3, user.getPass());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(stmt, con);
		}

	}

	@Override
	public User findById(String id) {
		Connection con=null;
		PreparedStatement stmt=null;
		ResultSet result=null;
		User user=null;
		String sql = new StringBuilder()
					.append("SELECT id, name, pass\n")
					.append("FROM user\n")
					.append("WHERE id=?")
					.toString();
		try {
			con = db.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, id);

			result = stmt.executeQuery();
			if(result.next()) {
				user = new User();
				user.setId(result.getString("id"));
				user.setName(result.getString("name"));
				user.setPass(result.getString("pass"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.close(result, stmt, con);
		}
		return user;
	}

}
