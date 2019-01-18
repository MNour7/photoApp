package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.School;

public interface SchoolRepository extends CrudRepository<School, Integer>{
	
	List<School> findAll();
	

}
