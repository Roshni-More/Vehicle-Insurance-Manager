package com.rt.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rt.Entity.Claim;
import com.rt.Service.ClaimService;
import com.rt.Service.PolicyService;

@Controller
public class ClaimController {

	@Autowired
	private ClaimService claimService;

	@Autowired
	private PolicyService policyService;

	// Upload directory for accident images
	private final String uploadDir = "D:/VehicleInsuranceUploads/";

	// 1️⃣ Show Add Claim Form
	@GetMapping("/insertclaims")
	public String showClaimForm(Model m) {
		m.addAttribute("policyList", policyService.getAllPolicies());
		m.addAttribute("claim", new Claim());
		return "Claim/addclaims";
	}

	// 2️⃣ Save Claim (Add New)
	@PostMapping("/saveClaim")
	public String saveClaim(@ModelAttribute Claim c,
			@RequestParam(value = "imageFile", required = false) MultipartFile file, HttpSession session)
			throws IOException {

		// 🔥 STEP 1: session se userId lo
		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		// 🔥 STEP 2: claim me userId set karo (THIS WAS MISSING)
		c.setAdminId(userId);

		// 🔥 STEP 3: image handling
		if (file != null && !file.isEmpty()) {
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			File f = new File(uploadDir + fileName);
			file.transferTo(f);
			c.setImageName(fileName);
		} else {
			c.setImageName(null);
		}

		c.setStatus("Pending");

		claimService.saveClaim(c);
		return "redirect:/claimshow";
	}

	// 3️⃣ Show All Claims
	@GetMapping("/claimshow")
	public String showClaims(Model m) {
		List<Claim> list = claimService.getAllClaims();
		m.addAttribute("claimList", list);
		return "Claim/claimshow";
	}

	// 4️⃣ Delete Claim
	@GetMapping("/deleteClaim")
	public String deleteClaim(@RequestParam int id) {
		claimService.deleteClaim(id);
		return "redirect:/claimshow";
	}

	// 5️⃣ Show Update Form (Edit Claim)
	@GetMapping("/editClaim")
	public String editClaim(@RequestParam int id, Model m) {
		Claim c = claimService.getClaimById(id);
		m.addAttribute("claim", c);
		m.addAttribute("policyList", policyService.getAllPolicies()); // For policy
		// dropdown
		return "Claim/editclaim";
	}

	// 7️⃣ Update Claim (WITHOUT IMAGE)
	@PostMapping("/updateClaim")
	public String updateClaim(@ModelAttribute Claim c, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		// 🔥 old data fetch (to keep status, image, userId safe)
		Claim oldClaim = claimService.getClaimById(c.getClaimId());

		c.setImageName(oldClaim.getImageName()); // image unchanged
		c.setStatus(oldClaim.getStatus()); // status unchanged
		c.setUserId(oldClaim.getUserId()); // user unchanged

		claimService.updateClaim(c);

		return "redirect:/claimshow";
	}

	// 6️⃣ Update Claim
	@RequestMapping("/updateClaimStatus")
	public String updateClaimStatus(@RequestParam int claimId, @RequestParam String status, HttpSession session) {

		Integer adminId = (Integer) session.getAttribute("sessionUserId");
		if (adminId == null) {
			return "redirect:/login";
		}

		Claim claim = claimService.getClaimById(claimId);

		if ("APPROVED".equalsIgnoreCase(status)) {
			claim.setStatus("APPROVED");
		} else if ("REJECTED".equalsIgnoreCase(status)) {
			claim.setStatus("REJECTED");
		} else {
			claim.setStatus("PENDING");
		}

		claimService.updateClaim(claim);

		return "redirect:/claimshow";
	}

}