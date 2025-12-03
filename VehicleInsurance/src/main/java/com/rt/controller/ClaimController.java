package com.rt.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rt.Entity.Claim;
import com.rt.Entity.Policy;
import com.rt.Service.ClaimService;
import com.rt.Service.PolicyService;

@Controller

public class ClaimController {

	@Autowired
	ClaimService claimservice;

	@Autowired
	PolicyService policyservice;

	// ------------------- SHOW CLAIM FORM -------------------
	@RequestMapping("addclaims")
	public String showClaimForm(Model model) {
		List<Policy> policyList = policyservice.getAllPolicies();
		model.addAttribute("policyList", policyList);
		model.addAttribute("claim", new Claim());
		return "Claim/claimForm";
	}

	// ------------------- INSERT CLAIM -------------------
	@RequestMapping("ClaimData")
	public String insertClaim(@ModelAttribute Claim claim, @RequestParam("accidentImage") MultipartFile file,
			HttpServletRequest request) {

		try {
			if (!file.isEmpty()) {
				String uploadDir = request.getServletContext().getRealPath("/resources/Upload/");

				File folder = new File(uploadDir);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				String fileName = file.getOriginalFilename();
				file.transferTo(new File(uploadDir + fileName));

				claim.setImageName(fileName);
			}

			claimservice.insertClaim(claim);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/claimshow";
	}

	// ------------------- SHOW ALL CLAIMS -------------------
	@RequestMapping("claimshow")
	public String showClaimList(Model model) {
		List<Claim> list = claimservice.getAllClaims();
		model.addAttribute("claimList", list);
		return "Claim/ClaimList";
	}

	// ------------------- EDIT CLAIM -------------------
	@RequestMapping("editclaim")
	public String editClaim(@RequestParam("claimId") int claimId, Model model) {
		Claim claim = claimservice.getClaimById(claimId);
		List<Policy> policyList = policyservice.getAllPolicies();
		model.addAttribute("policyList", policyList);
		model.addAttribute("claim", claim);
		return "Claim/updateClaim";
	}

	// ------------------- UPDATE CLAIM -------------------
	@RequestMapping("modifyClaim")
	public String updateClaim(@ModelAttribute Claim claim) {
		claimservice.updateClaim(claim);
		return "redirect:/claimshow";
	}

	// ------------------- SOFT DELETE -------------------
	@RequestMapping("deleteclaim")
	public String deleteClaim(@RequestParam("claimId") int claimId) {
		claimservice.deleteClaim(claimId);
		return "redirect:/claimshow";
	}
}