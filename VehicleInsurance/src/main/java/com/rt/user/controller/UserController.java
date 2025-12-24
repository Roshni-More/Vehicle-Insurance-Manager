package com.rt.user.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getuserlogin")
	public String userloginpage() {
		return "Userlogin"; // Userlogin.jsp
	}

	// 🔐 LOGIN
	@PostMapping("/loginpage")
	public String login(@RequestParam String username, @RequestParam String email, @RequestParam String password,
			HttpSession session, Model model) {

		String result = userService.login(username, email, password, session);

		switch (result) {

		case "FIRST_LOGIN":
			session.setAttribute("msg", "Password set successfully");
			return "redirect:/userindex";

		case "SUCCESS":
			return "redirect:/userindex";

		case "WRONG_PASSWORD":
			model.addAttribute("errormsg", "Wrong password");
			return "Userlogin";

		default:
			model.addAttribute("errormsg", "Invalid name or email");
			return "Userlogin";
		}
	}

	// 🏠 USER DASHBOARD
	@GetMapping("/userindex")
	public String userIndex(HttpSession session, Model model) {

		if (session.getAttribute("sessionUserId") == null) {
			return "redirect:/login";
		}

		// read message from session
		if (session.getAttribute("msg") != null) {
			model.addAttribute("msg", session.getAttribute("msg"));
			session.removeAttribute("msg");
		}

		return "userIndex";
	}

	// 🚪 LOGOUT
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

	// 🔑 LOGIN PAGE
	@GetMapping("/login")
	public String loginPage() {
		return "Userlogin";
	}
}
