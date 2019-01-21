package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.PhotoOrder;

public interface PhotoOrderRepository extends CrudRepository<PhotoOrder, Integer>{

	List<PhotoOrder> findByPhotoAppUserAppUserIdOrderByOrderDate(int id);

}
