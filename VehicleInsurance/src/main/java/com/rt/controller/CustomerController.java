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
import com.rt.Entity.Signup;
import com.rt.Service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@RequestMapping("add")
	public String showIndex() {
		return "Customer/addCustomer";
	}

	@RequestMapping("insert")
	public String insertdata(@ModelAttribute Customer customer, Model model, HttpSession session) {
		int userId = (int) session.getAttribute("sessionUserId");
		Signup s = new Signup();
		s.setSignupid(userId);
		customer.setUserId(s);
		customerservice.addcustomer(customer);
		return "redirect:/customershow";
	}

	@RequestMapping("customershow")
	public String showCustomerList(Model model) {
		List<Customer> list = customerservice.getAllCustomers();
		model.addAttribute("customerList", list);
		return "Customer/CustomerList";
	}

	@RequestMapping("editCustomer")
	public String updatedata(@RequestParam("customerId") int customerId, Model model) {
		Customer customer = customerservice.getCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "Customer/Updatecustomer";
	}

	@RequestMapping("updateCustomer")
	public String updateCustomer(@ModelAttribute Customer customer, Model model) {
		boolean isUpdated = customerservice.modifyCustomer(customer);
		if (isUpdated) {
			model.addAttribute("success", "Customer updated successfully");
		} else {
			model.addAttribute("fail", "Failed to update customer");
		}
		return "redirect:/customershow";
	}

	@RequestMapping("deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId, Model model) {
		boolean isDeleted = customerservice.softDeleteCustomer(customerId);
		if (isDeleted) {
			model.addAttribute("success", "Customer deleted successfully (soft delete)");
		} else {
			model.addAttribute("fail", "Failed to delete customer");
		}
		return "redirect:/customershow";
	}

}
