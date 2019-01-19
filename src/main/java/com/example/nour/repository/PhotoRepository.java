package com.example.nour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.Photo;

public interface PhotoRepository extends CrudRepository<Photo,Integer> {
	List<Photo> findAllByAppUserAppUserIdOrderByDateTake(int autho_id);
	List<Photo> findAllByChildChildIdAndAppUserAppUserIdOrderByDateTake(int child_id, int author_id);
	List<Photo> findAllByClazzClassIdAndAppUserAppUserIdOrderByDateTake(int class_id, int author_id);
	Photo findByPhotoId(int photo_id);
}
