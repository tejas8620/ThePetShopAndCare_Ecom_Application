package com.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@GetMapping("/products")
	public String product() {
		return "product";
	}
	
	
	@GetMapping("/product")
	public String viewProduct() {
		return "view_product";
	}
	
	
}
