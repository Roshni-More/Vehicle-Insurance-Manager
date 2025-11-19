package com.rt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rt.Entity.Customer;
import com.rt.Entity.Vehicle;
import com.rt.Service.CustomerService;
import com.rt.Service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	VehicleService vehicleservice;

	@Autowired
	CustomerService customerservice;

	@RequestMapping("addvehicle")
	public String dataadd(Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		model.addAttribute("vehicle", new Vehicle());
		List<Customer> customers = customerservice.getAllCustomer(userId);
		System.out.println("Customers loaded: " + customers.size());
		for (Customer c : customers) {
			System.out.println(c.getCustomerId() + " " + c.getCustomerName());
		}
		model.addAttribute("customerList", customers);
		return "Vehicle/addVehicle";
	}

	@RequestMapping("insertvehicle")
	public String vehicleadd(@ModelAttribute Vehicle vehicle, Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("sessionUserId");

		if (userId == null) {
			return "redirect:/login";
		}
		vehicle.setUserId(userId);

		boolean isadded = vehicleservice.vehicleInsert(vehicle);

		if (isadded) {
			model.addAttribute("success", "Vehicle added successfully!");
		} else {
			model.addAttribute("fail", "Failed to add vehicle!");
		}

		return "redirect:/vehiclelist";
	}

	@RequestMapping("vehiclelist")
	public String showVehicles(Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}
		List<Vehicle> list = vehicleservice.getAllVehicle(userId);
		model.addAttribute("vehicleList", list);

		return "Vehicle/vehicleshow";
	}

	@RequestMapping("editvehicle")
	public String editVehicle(@RequestParam("vehicleId") int vehicleId, Model model, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		Vehicle vehicle = vehicleservice.getVehicleById(vehicleId);
		if (vehicle == null) {
			return "redirect:/vehiclelist";
		}

		model.addAttribute("vehicle", vehicle);
		model.addAttribute("customerList", customerservice.getAllCustomer(userId));

		return "Vehicle/updateVehicle";
	}

	@RequestMapping("updatevehicle")
	public String updateVehicle(@ModelAttribute Vehicle vehicle, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		vehicle.setUserId(userId);
		vehicleservice.updateVehicle(vehicle);

		return "redirect:/vehiclelist";
	}

	@RequestMapping("deletevehicle")
	public String deleteVehicle(@RequestParam("vehicleId") int vehicleId, HttpSession session) {

		Integer userId = (Integer) session.getAttribute("sessionUserId");
		if (userId == null) {
			return "redirect:/login";
		}

		vehicleservice.deleteVehicledata(vehicleId);

		return "redirect:/vehiclelist";
	}

}
