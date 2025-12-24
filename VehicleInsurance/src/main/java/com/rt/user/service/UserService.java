package com.rt.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Entity.User;
import com.rt.user.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public String login(String name, String email, String password, HttpSession session) {

		User user = userDao.findByNameAndEmail(name, email);

		// ❌ name + email not found
		if (user == null) {
			return "INVALID_USER";
		}

		// ✅ first time password set
		if (user.getPassword() == null) {
			userDao.updatePassword(user.getUserId(), password);
			setSession(user, session);
			return "FIRST_LOGIN";
		}

		// ✅ normal login
		if (user.getPassword().equals(password)) {
			setSession(user, session);
			return "SUCCESS";
		}

		// ❌ wrong password
		return "WRONG_PASSWORD";
	}

	private void setSession(User user, HttpSession session) {
		session.setAttribute("sessionUserId", user.getUserId());
		session.setAttribute("sessionUsername", user.getUsername());
		session.setAttribute("sessionRole", user.getRole());
	}
}
