package com.rt.Login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.Entity.Signup;
import com.rt.Service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginservice;

	@RequestMapping("/getadminlogin")
	public String indexpage() {
		return "Admin/Adminlogin";
	}

	@RequestMapping("/adminpage")
	public String logindata(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		Signup user = loginservice.loginform(username, password);

		if (user != null) {

			// ✅ Set session
			session.setAttribute("sessionUserId", user.getAdminId());
			session.setAttribute("sessionUsername", user.getUsername());
			session.setAttribute("sessionUseremail", user.getEmail());

			System.out.println("LOGIN USER ID = " + user.getAdminId());
			System.out.println("LOGIN USERNAME = " + user.getUsername());

			// ✅ Single dashboard
			return "Admin/index";

		} else {
			model.addAttribute("errormsg", "Invalid Username or Password");
			return "Admin/Adminlogin";
		}
	}

	@RequestMapping("/signup")
	public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String email,

			@RequestParam String role, Model model) {

		boolean isAdded = loginservice.signup(username, password, email, role);

		if (isAdded) {
			model.addAttribute("msg", "Sign-Up Successful! Please Login.");
			return "Adminlogin";
		} else {
			model.addAttribute("errormsg", "Error while registering user");
			return "Admin/Adminlogin";
		}
	}

	@RequestMapping("/adminlogout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Admin/Adminlogin";
	}
}