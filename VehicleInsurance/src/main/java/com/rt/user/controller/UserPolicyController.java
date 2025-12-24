package com.rt.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rt.Entity.UserPolicy;
import com.rt.user.service.UserPolicyService;

@Controller
public class UserPolicyController {

	@Autowired
	UserPolicyService userpolicyservice;

	@GetMapping("/mypolicies")
	public String myPolicies(HttpSession session, Model model) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");

		if (userId == null) {
			return "redirect:/login";
		}

		List<UserPolicy> policyList = userpolicyservice.getUserPolicies(userId);

		model.addAttribute("policyList", policyList);

		return "UserPolicy/MyPolicies";
	}

}
