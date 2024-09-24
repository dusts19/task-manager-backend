package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
//	User findByUsername(String username);
//	User findByUsernameAndPassword(String username, String password);
	Optional<User> findByUsername(String username);
	Optional<User> findByUsernameAndPassword(String username, String password);
}
