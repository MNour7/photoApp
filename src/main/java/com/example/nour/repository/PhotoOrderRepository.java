package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.PhotoOrder;

public interface PhotoOrderRepository extends CrudRepository<PhotoOrder, Integer>{

	List<PhotoOrder> findByPhotoClazzSchoolSchoolIdOrderByOrderDate(int id);
	List<PhotoOrder> findByPhotoChildSchoolSchoolIdOrderByOrderDate(int schoolId);
	List<PhotoOrder> findByPhotoAppUserAppUserIdAndPhotoTypeOrderByOrderDate(int id, String string);

}
