package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	Optional<Role> findByName(Role.RoleName name);
}