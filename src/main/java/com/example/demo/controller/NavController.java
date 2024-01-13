package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	@GetMapping("/register")
	public String sendRegisterPage() {
		return "register";
	}
	@GetMapping("/log")
	public String sendLoginPage() {
		return "login";
	}
	@GetMapping("/newsong")
	public String newSong() {
		return "newSong";
	}



}
