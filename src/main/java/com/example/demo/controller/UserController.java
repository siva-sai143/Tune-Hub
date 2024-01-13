package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Users;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;
	@PostMapping("/registration")
	public String addUser(@ModelAttribute Users user) {
		boolean email_status=service.emailExist(user.getEmail());
		if(email_status==false) {
		service.addUser(user);
		System.out.println("user registered succesfully..!");
		}
		else{
			System.out.println("email already exist..!");
			
		}
		
	return "home";
	}
	@PostMapping("/validate")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session) {
		boolean login_status=service.validateUser(email,password);
		if(login_status==true) {
			String role=service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
		return "adminHome";
			}
			else {
				return "customerHome";
			}
		}
		else {
			System.out.println("wrong password");
			return "login";
			
		}
		
		
} 
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}

}
