package com.example.nour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nour.model.Class;
import com.example.nour.model.MyUserDetails;
import com.example.nour.model.School;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.SchoolRepository;

@Controller
@RequestMapping(path="/class")
public class ClassController {
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@GetMapping(path="/classes")
	public String Classes(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
		
		model.addAttribute("classes", classRepository.findAllBySchoolSchoolId(school.getSchoolId()));		
		model.addAttribute("school", school);
		return "Classes";
	}
	
	@GetMapping(path="/edit/{class_id}")
	public String edit(Model model, @PathVariable int class_id) {
		
		Class clazz = classRepository.findByClassId(class_id);		
				
		model.addAttribute("classForm", clazz);
		model.addAttribute("type", "edit");
		
		return "classEdit";
	}
	
	@GetMapping(path="/add")
	public String add(Model model) {
		
		Class clazz = new Class();
		
		model.addAttribute("classForm", clazz);
		model.addAttribute("type", "add");
		
		return "classEdit";
	}
	
	@PostMapping(path="/save")
	public String save(Model model, @ModelAttribute Class ClassForm, @RequestParam String type) {
		Class clazz;
		if(type.equals("edit")) {
			clazz = classRepository.findByClassId(ClassForm.getClassId());
		}
		else{
			clazz = new Class();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
			
			School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
			
			clazz.setSchool(school);
		}
		
		clazz.setName(ClassForm.getName());
		
		classRepository.save(clazz);
		
		return "redirect:classes";		
	}

}
