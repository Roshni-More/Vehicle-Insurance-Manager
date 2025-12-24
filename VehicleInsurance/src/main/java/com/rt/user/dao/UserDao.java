package com.rt.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rt.Entity.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate template;

	public User findByNameAndEmail(String name, String email) {
		String sql = "SELECT userId, username, emailAddress, password, isactive " + "FROM users "
				+ "WHERE username=? AND emailAddress=? AND isactive=1";

		try {
			return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name, email);
		} catch (EmptyResultDataAccessException e) {
			return null; // user not found
		}
	}

	public void updatePassword(int userId, String password) {
		String sql = "UPDATE users SET password=? WHERE userId=?";
		template.update(sql, password, userId);
	}
}
