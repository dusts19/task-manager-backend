package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Permission;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long>{
	Optional<Permission> findByName(String name);
}