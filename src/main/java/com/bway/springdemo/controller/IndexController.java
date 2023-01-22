package com.bway.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		
		return "loginform";
	}
	@GetMapping("/profile")
	public String getProfile() {
		
		return "Profile";
		
	}
	
}
