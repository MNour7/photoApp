package com.example.nour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.nour.model.Child;

public interface ChildRepository extends CrudRepository<Child,Integer>{
	Child findByChildId(int id);
	List<Child> findAllByClazzClassIdOrderByFirstname(int class_id);
	List<Child> findAllBySchoolSchoolId(int school_id);
	
	@Query("SELECT ch FROM School sc "
			+ "INNER JOIN Child ch ON sc.schoolId = ch.school.schoolId "
			+ "WHERE ch.childId IN (SELECT child.childId FROM Photo WHERE type = 'solo' AND appUser.appUserId =:userId) "
			+ "AND sc.schoolId =:schoolId "
			+ "GROUP BY ch.childId")
	List<Child> findBySchoolIdAndPhoto(@Param("userId") int userId, @Param("schoolId") int school_id);

	@Query("SELECT ch FROM School sc "
			+ "INNER JOIN Child ch ON sc.schoolId = ch.school.schoolId "
			+ "WHERE ch.childId IN (SELECT child.childId FROM Photo WHERE type = 'solo') "
			+ "AND sc.schoolId =:schoolId "
			+ "GROUP BY ch.childId")	
	List<Child> findAllHavePhoto(int schoolId);
}
