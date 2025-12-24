package com.rt.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rt.Entity.Vehicle;
import com.rt.user.service.UserVehicleService;

@Controller
public class VehicaleControlller {
	@Autowired
	UserVehicleService uservehicaleService;

	@GetMapping("/myvehicle")
	public String showMyVehicles(HttpSession session, Model model) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");

		if (userId == null) {
			return "redirect:/login";
		}

		List<Vehicle> vehicleList = uservehicaleService.getVehiclesByUserId(userId);
		model.addAttribute("vehicleList", vehicleList);

		return "UserVehicle/VehicleList";
	}
}
