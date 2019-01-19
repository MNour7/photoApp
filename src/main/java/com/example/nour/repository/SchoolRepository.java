package com.example.nour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.nour.model.School;

public interface SchoolRepository extends CrudRepository<School, Integer>{
	
	List<School> findAll();
	
	@Query("SELECT sc FROM School sc INNER JOIN Class cl ON sc.schoolId = cl.school.schoolId "
			+ "INNER JOIN Child ch ON sc.schoolId = ch.school.schoolId "
			+ "WHERE cl.classId IN(SELECT clazz.classId FROM Photo WHERE type = 'class' AND appUser.appUserId =:userId ) "
			+ "OR ch.childId IN (SELECT child.childId FROM Photo WHERE type = 'solo' AND appUser.appUserId =:userId) "
			+ "GROUP BY sc.schoolId")
	List<School> findSchoolTake(@Param("userId") int userId);

	School findBySchoolId(int school_id);
}
