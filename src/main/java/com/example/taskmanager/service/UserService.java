package com.example.taskmanager.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.exceptions.InvalidCredentialsException;
import com.example.taskmanager.exceptions.UserNotFoundException;
import com.example.taskmanager.model.Permission;
import com.example.taskmanager.model.Role;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.RoleRepo;
import com.example.taskmanager.repository.TaskRepo;
import com.example.taskmanager.repository.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo uRepo;
	
	@Autowired
	RoleRepo rRepo;
	
	@Autowired
	TaskRepo tRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> getUsers(){
		return uRepo.findAll();
	}
	
	public User getUserById(long id) {
		return uRepo.findById(id).orElse(new User());
	}
	
	@Transactional
	public User findByUsername(String username) {
		User user = uRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException("User not found"));
		Hibernate.initialize(user.getTasks());
		return user;
	}
	
//	public User findByUsername(String username) {
//		return uRepo.findByUsername(username)
//				.orElseThrow(() -> new RuntimeException("User not found"));
//	}
	
	@Transactional
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		if (user.getRoles() == null) {
			user.setRoles(new HashSet<>());
		}
		
		if (user.getRoles().isEmpty()) {
			Role userRole = rRepo.findByName(Role.RoleName.USER).orElseGet(() -> {
				Set<Permission> defaultPermissions = new HashSet<>();
				Permission readPermission = new Permission("READ", new HashSet<>());
				defaultPermissions.add(readPermission);
				Permission writePermission = new Permission("WRITE", new HashSet<>());
				defaultPermissions.add(writePermission);
				
				Role newRole = new Role(Role.RoleName.USER, defaultPermissions);
				
				rRepo.save(newRole);
				
				for (Permission permission : defaultPermissions) {
					permission.getRoles().add(newRole);
				}				
				return newRole;
			});
//			Role userRole = rRepo.findByName(Role.RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
			user.getRoles().add(userRole);
		
		}
		for (Role role : user.getRoles()) {
			role.getUsers().size();
		}
		
		System.out.println("User roles before saving: " + user.getRoles());
		
		return uRepo.save(user);
	}
//	public void addUser(User user) {
//		uRepo.save(user);
//	}

	public User updateUser(long id, User user) {
		User existingUser = uRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		existingUser.setUsername(user.getUsername());
		existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return uRepo.save(existingUser);
	}
//	public void updateUser(User user) {
//		uRepo.save(user);
//	}

	public void deleteUser(long id) {
		uRepo.deleteById(id);
	}
	
	public Task createTaskForUser(long id, Task task) {
		User user = uRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		task.setUser(user);
		return tRepo.save(task);
	}
	
	public User authenticateUser(String username, String password) {
		User user = uRepo.findByUsername(username)
				.orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));
		
		if (passwordEncoder.matches(password, user.getPassword())) {
			return user;
		} else {
			throw new InvalidCredentialsException("Invalid username or password");
		}
		
	}
//	public User authenticateUser(String username, String password) {
//		return uRepo.findByUsernameAndPassword(username, password)
//				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
//	}
}
