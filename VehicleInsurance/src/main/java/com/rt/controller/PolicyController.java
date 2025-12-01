package com.rt.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.Entity.Policy;
import com.rt.Entity.Vehicle;
import com.rt.Service.PolicyService;
import com.rt.Service.VehicleService;

@Controller
public class PolicyController {

	@Autowired
	VehicleService vehicleservice;

	@Autowired
	PolicyService policyservice;

	// ADD PAGE
	@RequestMapping("policyadd")
	public String addpolicydata(Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		List<Vehicle> list = vehicleservice.getVehiclesByUser(userId);
		model.addAttribute("vehicleList", list);

		return "Policy/addPolicy";
	}

	// INSERT DATA
	@RequestMapping("insertPolicy")
	public String insertpolicydata(@ModelAttribute Policy policy, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		policy.setUserId(userId);
		policy.setStatus("Pending");
		policy.setExpiryDate(LocalDate.now().plusYears(1));
		policy.setRenewCount(0);

		policyservice.insertPolicy(policy);

		return "redirect:/Policyshow";
	}

	// SHOW LIST
	@RequestMapping("Policyshow")
	public String showPolicyList(Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		List<Policy> list = policyservice.getPoliciesByUser(userId);
		model.addAttribute("policyList", list);

		return "Policy/policyList";
	}

	// EDIT PAGE
	@RequestMapping("editPolicy")
	public String editPolicy(@RequestParam int policyId, Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");

		Policy policy = policyservice.getPolicyById(policyId);

		List<Vehicle> vehicleList = vehicleservice.getVehiclesByUser(userId);
		List<String> policyTypes = Arrays.asList("Third Party", "Comprehensive", "Basic");

		model.addAttribute("policy", policy);
		model.addAttribute("vehicleList", vehicleList);
		model.addAttribute("policyTypes", policyTypes);

		return "Policy/updatepolicy";
	}

	// UPDATE DATA
	@RequestMapping("updatePolicydata")
	public String updatePolicy(@ModelAttribute Policy policy, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");

		policy.setUserId(userId);
		policyservice.updatePolicy(policy);

		return "redirect:/Policyshow";
	}

	// DELETE
	@RequestMapping("deletePolicy")
	public String deletePolicy(@RequestParam int policyId) {

		policyservice.deletePolicy(policyId);
		return "redirect:/Policyshow";
	}

	// RENEW POLICY
	@RequestMapping("renewPolicy")
	public String renewPolicy(@RequestParam int policyId, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		Policy oldPolicy = policyservice.getPolicyById(policyId);
		oldPolicy.setExpiryDate(oldPolicy.getExpiryDate().plusYears(1));

		// Update values
		oldPolicy.setExpiryDate(oldPolicy.getExpiryDate().plusYears(1));
		oldPolicy.setStatus("Active");
		oldPolicy.setRenewCount(oldPolicy.getRenewCount() + 1);
		oldPolicy.setRenewalDate(LocalDate.now());

		oldPolicy.setUserId(userId);
		policyservice.updatePolicy(oldPolicy);

		return "redirect:/Policyshow";
	}
}
