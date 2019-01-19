package com.example.nour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.nour.model.Class;

public interface ClassRepository extends CrudRepository<Class, Integer>{
	Class findByClassId(int id);
	List<Class> findAllBySchoolSchoolId(int school_id);
	
	@Query("SELECT cl FROM School sc INNER JOIN Class cl ON sc.schoolId = cl.school.schoolId "
			+ "WHERE cl.classId IN(SELECT clazz.classId FROM Photo WHERE type = 'class' AND appUser.appUserId =:userId ) "
			+ "AND sc.schoolId =:schoolId "
			+ "GROUP BY cl.classId")
	List<Class> findBySchoolIdAndPhoto(@Param("userId") int userId, @Param("schoolId") int school_id);
	
}
