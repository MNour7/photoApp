package com.example.nour.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nour.model.MyUserDetails;
import com.example.nour.model.School;
import com.example.nour.model.PhotoOrder;
import com.example.nour.repository.AppUserRepository;
import com.example.nour.repository.ChildRepository;
import com.example.nour.repository.ClassRepository;
import com.example.nour.repository.PhotoRepository;
import com.example.nour.repository.SchoolRepository;
import com.example.nour.repository.PhotoOrderRepository;

@Controller
@RequestMapping(path = "/admin")
public class AdministratorController {

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

	@GetMapping(path = "/home")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();

		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());

		model.addAttribute("school", school);
		model.addAttribute("classes", classRepository.findAllHavePhoto(school.getSchoolId()));
		model.addAttribute("childs", childRepository.findAllHavePhoto(school.getSchoolId()));

		return "schoolHome";
	}

	@GetMapping(path = "/classPhotos/{class_id}")
	public String classPhotos(@PathVariable int class_id, Model model) {

		model.addAttribute("photos", photoRepository.findAllByClazzClassIdOrderByDateTake(class_id));
		model.addAttribute("classe", classRepository.findByClassId(class_id));

		return "classPhotos";
	}

	@GetMapping(path = "/childPhotos/{child_id}")
	public String childPhotos(@PathVariable int child_id, Model model) {

		model.addAttribute("photos", photoRepository.findAllByChildChildIdOrderByDateTake(child_id));
		model.addAttribute("child", childRepository.findByChildId(child_id));

		return "childPhotos";
	}

	@GetMapping(path = "/totalSales")
	public String sales(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails myUser = (MyUserDetails) auth.getPrincipal();

		School school = schoolRepository.findByAppUserAppUserId(myUser.getId());

		List<PhotoOrder> soloOrder = photoOrderRepository
				.findByPhotoChildSchoolSchoolIdOrderByOrderDate(school.getSchoolId());
		List<PhotoOrder> classOrder = photoOrderRepository
				.findByPhotoClazzSchoolSchoolIdOrderByOrderDate(school.getSchoolId());

		double totalSolo = soloOrder.size() * school.getSoloPrice();
		double totalClass = classOrder.size() * school.getGroupPrice();
		double totalAll = totalSolo + totalClass;

		model.addAttribute("classOrders", classOrder);
		model.addAttribute("soloOrders", soloOrder);
		model.addAttribute("totalSolo", totalSolo);
		model.addAttribute("totalAll",totalAll);
		model.addAttribute("totalClass", totalClass);

		return "totalSales";
	}
}
