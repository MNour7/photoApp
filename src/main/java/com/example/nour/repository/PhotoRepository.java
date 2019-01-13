package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo,Integer> {
	List<Photo> findAllByAuthorIdOrderByDateTake(int autho_id);
	List<Photo> findAllByChildOrderByDateTake(int child_id);
	List<Photo> findAllByClazzOrderByDateTake(int class_id);
}
