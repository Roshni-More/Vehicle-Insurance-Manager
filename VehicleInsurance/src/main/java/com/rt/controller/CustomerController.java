package com.rt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

	@RequestMapping("add")
	public String showIndex() {
		return "Customer/addCustomer";
	}

}
