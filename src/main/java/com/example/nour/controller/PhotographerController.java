package com.example.nour.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContextListener;

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
import com.example.nour.model.School;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.PhotoOrderRepository;
import com.example.nour.repository.PhotoRepository;
import com.example.nour.repository.SchoolRepository;

@Controller
@RequestMapping(path = "/photographer")
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
	
	@Autowired
	private PhotoOrderRepository photoOrderRepository;

	private ModelMapper mapper = new ModelMapper();

	@GetMapping(path = "/shop")
	public String myshop(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		List<School> listSch = schoolRepository.findSchoolTake(myUser.getId());

		model.addAttribute("schools", listSch);
		model.addAttribute("photos", photoRepository.findAllByAppUserAppUserIdOrderByDateTake(myUser.getId()));

		return "photoshop";
	}

	@GetMapping(path = "/school/{school_id}")
	public String school(@PathVariable int school_id, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();

		model.addAttribute("school", schoolRepository.findBySchoolId(school_id));
		model.addAttribute("childs", childRepository.findBySchoolIdAndPhoto(myUser.getId(), school_id));
		model.addAttribute("classes", classRepository.findBySchoolIdAndPhoto(myUser.getId(), school_id));

		return "schoolPhoto";
	}

	@GetMapping(path = "/classPhotos/{class_id}")
	public String classPhotos(@PathVariable int class_id, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();

		model.addAttribute("photos",
				photoRepository.findAllByClazzClassIdAndAppUserAppUserIdOrderByDateTake(class_id, myUser.getId()));
		model.addAttribute("classe", classRepository.findByClassId(class_id));

		return "classPhotos";
	}

	@GetMapping(path = "/childPhotos/{child_id}")
	public String childPhotos(@PathVariable int child_id, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();

		model.addAttribute("photos",
				photoRepository.findAllByChildChildIdAndAppUserAppUserIdOrderByDateTake(child_id, myUser.getId()));
		model.addAttribute("child", childRepository.findByChildId(child_id));

		return "childPhotos";
	}

	@GetMapping(path = "/addPhoto")
	public String addPhoto(Model model) {

		model.addAttribute("schools", schoolRepository.findAll());
		model.addAttribute("photoForm", new Photo());

		return "addPhoto";
	}

	@GetMapping(path = "/loadClasses/{idSchool}")
	public @ResponseBody String loadClasses(@PathVariable int idSchool) {

		List<Class> listClasses = classRepository.findAllBySchoolSchoolId(idSchool);
		List<ClassDTO> listDTO = new ArrayList<>();
		
		String classes = "";
		for (int i = 0; i < listClasses.size(); i++) {
			listDTO.add(mapper.map(listClasses.get(i), ClassDTO.class));
			if (classes.equals(""))
				classes += listClasses.get(i).getClassId() + "=" + listClasses.get(i).getName();
			else
				classes += "|" + listClasses.get(i).getClassId() + "=" + listClasses.get(i).getName();
		}

		return classes;
	}

	@GetMapping(path = "/loadChilds/{classId}")
	public @ResponseBody String loadChilds(@PathVariable int classId) {

		List<Child> listChilds = childRepository.findAllByClazzClassIdOrderByFirstname(classId);
		String childs = "";
		for (int i = 0; i < listChilds.size(); i++) {

			if (childs.equals(""))
				childs += listChilds.get(i).getChildId() + "=" + listChilds.get(i).getFirstname() + " "
						+ listChilds.get(i).getLastname();
			else
				childs += "|" + listChilds.get(i).getChildId() + "=" + listChilds.get(i).getFirstname() + " "
						+ listChilds.get(i).getLastname();
		}

		return childs;
	}
	
	@GetMapping(path = "/sales")
	public String sales(@PathVariable int classId, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		
		model.addAttribute("classOrders", photoOrderRepository.findByPhotoAppUserAppUserIdOrderByOrderDate(myUser.getId()));

		return "photoSales";
	}

	@PostMapping(path = "/savePhoto")
	public String savePhoto(@RequestParam("file") MultipartFile file, @RequestParam("classId") int classId,
			@RequestParam("childId") String childId, Model model, @ModelAttribute Photo photoForm) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();
		Photo photo = new Photo();

		photo.setName(photoForm.getName());
		photo.setType(photoForm.getType());
		photo.setDateTake(new Date());
		if (photo.getType().equals("class")) {
			Class clazz = classRepository.findByClassId(classId);
			photo.setClazz(clazz);
		} else {
			Child child = childRepository.findByChildId(Integer.parseInt(childId));
			photo.setChild(child);
		}

		AppUser appUser = appUserRepository.findByAppUserId(myUser.getId());
		photo.setAppUser(appUser);

		String uuid = UUID.randomUUID().toString() + ".jpg";
		// Storing File
		saveFile(file, uuid);

		photo.setPath(uuid);

		photoRepository.save(photo);

		return "redirect:shop";
	}

	public void saveFile(MultipartFile file, String uuid) {
		URL url = ServletContextListener.class.getClassLoader().getResource("static/images/");

		try {
			if (!file.isEmpty()) {
				// System.err.println(filename);
				BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				File destination = new File(url.getPath() + uuid);
				ImageIO.write(src, "jpg", destination);
			}
		} catch (Exception e) {
			System.out.println("Exception occured" + e.getMessage());
		}
	}

}
