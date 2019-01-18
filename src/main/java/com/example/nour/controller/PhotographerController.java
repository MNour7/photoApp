package com.example.nour.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.nour.model.AppUser;
import com.example.nour.model.Child;
import com.example.nour.model.Class;
import com.example.nour.model.ClassDTO;
import com.example.nour.model.MyUserDetails;
import com.example.nour.model.Photo;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.PhotoRepository;
import com.example.nour.repository.SchoolRepository;

@Controller
@RequestMapping(path="/photographer")
public class PhotographerController {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ChildRepository childRepository;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping(path="/shop")
	public String myshop(Model model) {				
		
		return "photoshop";
	}
	
	@GetMapping(path="/addPhoto")
	public String addPhoto(Model model) {
				
		model.addAttribute("schools", schoolRepository.findAll());
		model.addAttribute("photoForm", new Photo());
		
		return "addPhoto";
	}
	
	@GetMapping(path="/loadClasses/{idSchool}")
	public @ResponseBody String loadClasses(@PathVariable int idSchool) {
		
		List<Class> listClasses = classRepository.findAllBySchoolSchoolId(idSchool);
		List<ClassDTO> listDTO = new ArrayList<>();
		System.err.println("list size = "+listClasses.size());
		String classes ="";
		for(int i = 0; i < listClasses.size(); i++) {
			listDTO.add(mapper.map(listClasses.get(i), ClassDTO.class));
			if(classes.equals(""))
				classes += listClasses.get(i).getClassId()+"="+listClasses.get(i).getName();
			else
				classes += "|"+listClasses.get(i).getClassId()+"="+listClasses.get(i).getName();
		}
		
		return classes;
	}
	
	@GetMapping(path="/loadChilds/{classId}")
	public @ResponseBody String loadChilds(@PathVariable int classId) {
		
		List<Child> listChilds = childRepository.findAllByClazzClassIdOrderByFirstname(classId);
		String childs ="";
		for(int i = 0; i < listChilds.size(); i++) {
			
			if(childs.equals(""))
				childs += listChilds.get(i).getChildId()+"="+listChilds.get(i).getFirstname()+" "+listChilds.get(i).getLastname();
			else
				childs += "|"+listChilds.get(i).getChildId()+"="+listChilds.get(i).getFirstname()+" "+listChilds.get(i).getLastname();
		}
		
		return childs;
	}
	
	@PostMapping(path="/savePhoto")
	public String savePhoto(@RequestParam("file") MultipartFile  file, Model model,
			@ModelAttribute Photo photoForm) throws IOException {
		
		saveFile(file);
		
//		Photo photo = new Photo();
//		
//		photo.setName(photoForm.getName());
//		photo.setType(photoForm.getType());
//		photo.setDateTake(new Date());
//		if(photo.getType().equals("solo")) {
//			Child child = childRepository.findByChildId(photoForm.getChild().getChildId());
//			photo.setChild(child);
//		}else {
//			Class clazz = classRepository.findByClassId(photoForm.getClazz().getClassId());
//			photo.setClazz(clazz);
//		}
//		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
//		AppUser appUser = appUserRepository.findByAppUserId(myUser.getId());
//		photo.setAppUser(appUser);
//		photo.setImage(file.getBytes());
//		
//		photoRepository.save(photo);
		
		return "redirect:shop";
	}
	
	public void saveFile(MultipartFile file) {
		String webappRoot = servletContext.getRealPath("/");
	    //System.out.println(user.toString());
	    try {
	        if (!file.isEmpty()) {
	        	 String filename = file.getOriginalFilename();
	        	 System.err.println(filename);
	             BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
	             File destination = new File("/resources/images/"+filename); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
	             ImageIO.write(src, "jpg", destination);
	       
	             System.err.println("Image is stored at ");
	        } 
	    } catch (Exception e) {
	        System.out.println("Exception occured" + e.getMessage());
	    }
	}

}
