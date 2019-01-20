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

import com.example.nour.model.AppUser;
import com.example.nour.model.Child;
import com.example.nour.model.MyUserDetails;
import com.example.nour.model.School;
import com.example.nour.model.UserRole;
import com.example.nour.model.UserService;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.PhotoRepository;
import com.example.nour.repository.RoleRepository;
import com.example.nour.repository.SchoolRepository;
import com.example.nour.repository.UserRoleRepository;

@Controller
@RequestMapping(path="/parent")
public class ParentController {
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/home")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		model.addAttribute("childs", childRepository.findAllByAppUserAppUserIdOrderByFirstname(myUser.getId()));
		
		return "parentHome";
	}
	
	@GetMapping(path="/parents")
	public String parents(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());
		
		model.addAttribute("parents", appUserRepository.findAllParent(school.getSchoolId()));		
		model.addAttribute("school", school);
		return "parents";
	}
	
	@GetMapping(path="/edit/{parent_id}")
	public String edit(Model model, @PathVariable int parent_id) {
		
		AppUser parent = appUserRepository.findByAppUserId(parent_id);
		parent.setPassword("");
		
		model.addAttribute("parentForm", parent);
		model.addAttribute("type", "edit");
		
		return "parentEdit";
	}
	
	@GetMapping(path="/add")
	public String add(Model model) {
		
		AppUser parent = new AppUser();
		
		model.addAttribute("parentForm", parent);
		model.addAttribute("type", "add");
		
		return "parentEdit";
	}
	
	@PostMapping(path="/save")
	public String save(Model model, @ModelAttribute AppUser parentForm, @RequestParam String type) {
		AppUser appUser;
		if(type.equals("edit")) {
			appUser = appUserRepository.findByAppUserId(parentForm.getAppUserId());
			appUser.setFirstname(parentForm.getFirstname());
			appUser.setLastname(parentForm.getLastname());
			appUser.setEmail(parentForm.getEmail());
			if(!parentForm.getPassword().equals("")) {
				appUser.setPassword(parentForm.getPassword());
				userService.saveUser(appUser);
			}
			else
				appUserRepository.save(appUser);
		}
		else{
			appUser = new AppUser();
			appUser.setFirstname(parentForm.getFirstname());
			appUser.setLastname(parentForm.getLastname());
			appUser.setEmail(parentForm.getEmail());
			if(!parentForm.getPassword().equals("")) {
				appUser.setPassword(parentForm.getPassword());
				appUser = userService.saveUser(appUser);
			}
			else
				appUser = appUserRepository.save(appUser);
			
			UserRole userRole = new UserRole();
			userRole.setAppUser(appUser);
			userRole.setRole(roleRepository.findByRoleId(3));
			
			userRoleRepo.save(userRole);
		}			
		
		return "redirect:parents";		
	}
	
	@GetMapping(path="/childPhotos/{child_id}")
	public String childPhotos(@PathVariable int child_id, Model model) {
		
		Child child = childRepository.findByChildId(child_id);
		
		model.addAttribute("solophotos", photoRepository.findAllByChildChildIdOrderByDateTake(child_id));
		model.addAttribute("classphotos", photoRepository.findAllByClazzClassIdOrderByDateTake(child.getClazz().getClassId()));
		model.addAttribute("child", child);
		
		return "parentChildPhotos";
	}

}