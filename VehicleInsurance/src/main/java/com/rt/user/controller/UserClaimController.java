package com.rt.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rt.Entity.UserClaim;
import com.rt.user.service.UserClaimService;

@Controller
public class UserClaimController {

	@Autowired
	UserClaimService claimservice;

	@RequestMapping("/myclaims")
	public String claimform(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}
		List<UserClaim> claimList = claimservice.getUserClaim(userId);
		model.addAttribute("claimList", claimList);
		return "UserClaim/Myclaim";
	}

}
