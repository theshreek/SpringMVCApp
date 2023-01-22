package com.bway.springdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bway.springdemo.model.User;
import com.bway.springdemo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	private static final Logger logger =LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/loginform")
	public String showLoginForm() {
		
		return "loginform";
		
	}
	
	@PostMapping("/login")
	public String doLogin(@ModelAttribute User user, Model model, HttpSession session) {
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User usr= userRepo.findUser(user.getEmail(), user.getPassword());
		
		
		if(usr != null) {
			
			logger.info("user login successfully");
			
			session.setAttribute("user", usr);
			session.setMaxInactiveInterval(100);
			//model.addAttribute("uname", user.getEmail());
			
			return "home";
			
		}
		
		logger.info("user login failed");

		model.addAttribute("message","Incorrect Email Address / Password");
		return "loginform";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("user logout successfully");

		session.invalidate();//session kill
		return "loginform";
	}

}
