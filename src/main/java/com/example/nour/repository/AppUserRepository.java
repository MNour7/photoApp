package com.example.nour.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
	AppUser findByEmail(String email);
}
