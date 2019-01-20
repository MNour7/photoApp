package com.example.nour.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.nour.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRoleId(int id);
}
