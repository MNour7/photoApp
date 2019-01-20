package com.example.nour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.nour.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
	AppUser findByEmail(String email);
	AppUser findByAppUserId(int id);
	
	@Query("SELECT us FROM Child ch "
			+ "INNER JOIN AppUser us ON ch.appUser.appUserId = us.appUserId "
			+ "WHERE ch.school.schoolId =:schoolId "
			+ "GROUP BY us.appUserId")
	List<AppUser> findAllParent(@Param("schoolId") int school_id);
}
