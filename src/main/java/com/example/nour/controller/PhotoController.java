package com.example.nour.controller;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.nour.model.AppUser;
import com.example.nour.model.Child;
import com.example.nour.model.Class;
import com.example.nour.model.MyUserDetails;
import com.example.nour.model.Photo;
import com.example.nour.model.PhotoOrder;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.PhotoOrderRepository;
import com.example.nour.repository.PhotoRepository;

@Controller
@RequestMapping(path="photo")
public class PhotoController {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private ClassRepository classRepository;
	
	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private PhotoOrderRepository photoOrderRepository;
	
	@GetMapping(path="/editPhoto/{photo_id}/{school_id}")
	public String editPhoto(Model model, @PathVariable("photo_id") int photo_id, 
			@PathVariable("school_id") int school_id) {
		
		model.addAttribute("childs", childRepository.findAllBySchoolSchoolId(school_id));
		model.addAttribute("classes", classRepository.findAllBySchoolSchoolId(school_id));
		model.addAttribute("photoForm", photoRepository.findByPhotoId(photo_id));
		
		return "editPhoto";
	}
	
	@PostMapping(path="/saveEditPhoto")
	public String saveEditPhoto(Model model, @ModelAttribute Photo photoForm){
		
		Photo photo = photoRepository.findByPhotoId(photoForm.getPhotoId());
		
		photo.setName(photoForm.getName());
		photo.setType(photoForm.getType());
		if(photo.getType().equals("class")) {
			Class clazz = classRepository.findByClassId(photoForm.getClazz().getClassId());
			photo.setClazz(clazz);
			photo.setChild(null);
		}else {
			Child child = childRepository.findByChildId(photoForm.getChild().getChildId());
			photo.setChild(child);
			photo.setClazz(null);
		}
		
		photoRepository.save(photo);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		boolean isPhoto = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//	        System.err.println("collection size = " + authorities.size() +" Content = "+authorities.toString());
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_PHOTO")) {
            	isPhoto = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
 
        if (isPhoto) {
            return "redirect:/photographer/shop";
        }
		
		return "redirect:/admin/home";
	}
	
	@GetMapping(path="/deletePhoto/{photo_id}")
	public @ResponseBody String deletePhoto(@PathVariable int photo_id) {
		
		Photo photo = photoRepository.findByPhotoId(photo_id);
		
		if(delete(photo.getPath())) {
			photoRepository.delete(photo);
			return "OK";
		}
		
		return "NO";
		
	}
	
	public boolean delete(String path) {
		boolean bool = false;
		URL url = ServletContextListener.class.getClassLoader().getResource("static/images/");
		
		try{    		
    		File file = new File(url.getPath()+path);        	
    		if(file.delete()){
    			bool=true;
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			bool=false;
    			System.out.println("Delete operation is failed.");
    		}
    	   
    	}catch(Exception e){    		
    		e.printStackTrace();    		
    	}
		
		return bool;
	}
	
	@GetMapping(path="/orderPhoto/{photo_id}")
	public @ResponseBody String orderPhoto(@PathVariable int photo_id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		AppUser parent = appUserRepository.findByAppUserId(myUser.getId());
		
		Photo photo = photoRepository.findByPhotoId(photo_id);
		
		PhotoOrder order = new PhotoOrder();
		order.setAppUser(parent);
		order.setPhoto(photo);
		order.setOrderDate(new Date());
		order.setQuantity(1);
		
		photoOrderRepository.save(order);
		
		return "OK";		
	}

}
