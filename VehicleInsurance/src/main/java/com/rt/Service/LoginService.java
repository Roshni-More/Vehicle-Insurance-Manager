package com.rt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rt.Dao.Logindao;
import com.rt.Entity.Signup;

@Service

public class LoginService {

	@Autowired
	Logindao logindao;

	public Signup loginform(String username, String password) {
		return logindao.loginform(username, password);
	}

	public boolean signup(String username, String password, String email, String role) {
		return logindao.signup(username, password, email, role);
	}

}
