package com.example.nour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nour.repository.PhotoRepository;

@Controller
@RequestMapping(path="/photographer")
public class PhotographerController {
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@GetMapping(path="/shop")
	public String myshop(Model model) {
		
		
		
		return "photoshop";
	}

}
