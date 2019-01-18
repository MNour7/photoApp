package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.Child;

public interface ChildRepository extends CrudRepository<Child,Integer>{
	Child findByChildId(int id);
	List<Child> findAllByClazzClassIdOrderByFirstname(int class_id);
}
