package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.Class;

public interface ClassRepository extends CrudRepository<Class, Integer>{
	Class findByClassId(int id);
	List<Class> findAllBySchoolSchoolId(int school_id);
}
