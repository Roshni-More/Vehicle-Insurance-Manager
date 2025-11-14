package com.rt.Login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.Service.LoginService;
import com.rt.Entity.Signup;

@Controller
public class LoginController {

	@Autowired
	LoginService loginservice;

	@RequestMapping("/loginpage")
	public String logindata(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		Signup user = loginservice.loginform(username, password);

		if (user != null) {
			session.setAttribute("sessionUserId", user.getSignupid());
			session.setAttribute("sessionUsername", user.getUsername());
			session.setAttribute("sessionRole", user.getRole());

			return "index";
		} else {
			model.addAttribute("errormsg", "Invalid Username or Password");
			return "Login";
		}
	}

	@RequestMapping("/signup")
	public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String email,
			@RequestParam String role, Model model) {

		boolean isAdded = loginservice.signup(username, password, email, role);

		if (isAdded) {
			model.addAttribute("msg", "Sign-Up Successful! Please Login.");
			return "Login";
		} else {
			model.addAttribute("errormsg", "Error while registering user");
			return "Login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Login";
	}
}