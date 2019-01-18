package com.example.nour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/school")
public class AdministratorController {
	
	
	@GetMapping(path="/home")
	public String home(Model model) {
		
		return "school";
	}
}
