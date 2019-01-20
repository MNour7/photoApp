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

import com.example.nour.model.Child;
import com.example.nour.model.MyUserDetails;
import com.example.nour.model.School;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.SchoolRepository;

@Controller
@RequestMapping(path="/child")
public class ChildController {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@GetMapping(path="/childs")
	public String childs(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
		
		model.addAttribute("childs", childRepository.findAllBySchoolSchoolId(school.getSchoolId()));		
		model.addAttribute("school", school);
		return "childs";
	}
	
	@GetMapping(path="/edit/{child_id}")
	public String edit(Model model, @PathVariable int child_id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
		
		Child child = childRepository.findByChildId(child_id);		
				
		model.addAttribute("childForm", child);
		model.addAttribute("type", "edit");
		model.addAttribute("classes", classRepository.findAllBySchoolSchoolId(school.getSchoolId()));
		model.addAttribute("parents", appUserRepository.findAll());
		
		return "childEdit";
	}
	
	@GetMapping(path="/add")
	public String add(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
		
		Child child = new Child();
		
		model.addAttribute("childForm", child);
		model.addAttribute("type", "add");
		model.addAttribute("classes", classRepository.findAllBySchoolSchoolId(school.getSchoolId()));
		model.addAttribute("parents", appUserRepository.findAll());
		
		return "childEdit";
	}
	
	@PostMapping(path="/save")
	public String save(Model model, @ModelAttribute Child childForm, @RequestParam String type) {
		Child child;
		if(type.equals("edit")) {
			child = childRepository.findByChildId(childForm.getChildId());
		}
		else{
			child = new Child();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
			
			School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
			
			child.setSchool(school);
		}
		
		child.setFirstname(childForm.getFirstname());
		child.setLastname(childForm.getLastname());
		child.setAppUser(appUserRepository.findByAppUserId(childForm.getAppUser().getAppUserId()));
		child.setClazz(classRepository.findByClassId(childForm.getClazz().getClassId()));
		
		childRepository.save(child);
		
		return "redirect:childs";		
	}

}
