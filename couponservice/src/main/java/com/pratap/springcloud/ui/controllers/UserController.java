package com.pratap.springcloud.ui.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pratap.springcloud.security.SecurityService;
import com.pratap.springcloud.security.entities.UserEntity;
import com.pratap.springcloud.security.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(String email, String password) {
		boolean loginResponse = securityService.login(email, password);
		
		if(loginResponse) {
			return "/index";
		}
		return "login";
	}
	
	// user registration
	@GetMapping("/showReg")
	public String showRegistrationPage() {
		return "registerUser";
	}
	
	@PostMapping("/registerUser")
	public String register(UserEntity user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login";
	}
}
