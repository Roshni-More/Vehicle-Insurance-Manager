package com.rt.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.Entity.Signup;

@Repository

public class Logindao {

	@Autowired
	private JdbcTemplate template;

	public Signup loginform(String username, String password) {
		try {
			String sql = "SELECT * FROM signup WHERE username = ? AND password = ?";

			return template.queryForObject(sql, new Object[] { username, password }, (rs, rowNum) -> {

				Signup s = new Signup();
				s.setSignupid(rs.getInt("signupid"));
				s.setUsername(rs.getString("username"));
				s.setEmail(rs.getString("email"));
				s.setPassword(rs.getString("password"));
				s.setRole(rs.getString("role"));

				return s;
			});

		} catch (Exception e) {
			return null;
		}
	}

	public boolean signup(String username, String password, String email, String role) {
		try {
			String sql = "INSERT INTO signup (username, password, email, role) VALUES (?, ?, ?, ?)";
			int result = template.update(sql, username, password, email, role);
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
