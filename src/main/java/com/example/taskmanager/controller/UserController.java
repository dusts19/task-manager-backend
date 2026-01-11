package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.UserDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://dailydirector.vercel.app"})
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getUsers(){		
		return ResponseEntity.ok(userService.getUsers());
	}

//	@GetMapping
//	public ResponseEntity<List<UserDTO>> getUsers(){
//		List<UserDTO> users = userService.getUsers()
//				.stream()
//				.map(u -> new UserDTO(u.getId(), u.getUsername()))
//				.toList();
//		
//		return ResponseEntity.ok(users);
//	}
	

//	@GetMapping
//	public ResponseEntity<List<User>> getUsers(){
//		List<User> users = service.getUsers();
//		return ResponseEntity.ok(users);
//	}
//	
	
//	@GetMapping("/{id}")
//	@Transactional
//	public ResponseEntity<User> getUserById(@PathVariable long id) {
//		User user = service.getUserById(id);
//		return ResponseEntity.ok(user);
//	}
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
		User user = userService.getUserById(id);
		UserDTO userDTO = new UserDTO(user.getId(), user.getUsername());
		return ResponseEntity.ok(userDTO);
	}
	
	@GetMapping("/current")
	public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username = (principal instanceof UserDetails) // new
//			? ((UserDetails) principal).getUsername()
//			: principal.toString();
//		User user = userService.findByUsername(username);
//		UserDTO dto = new UserDTO(user.getId(), user.getUsername()); // new
//		
//		return ResponseEntity.ok(dto);
		return ResponseEntity.ok(userService.getCurrentUserDTO(userDetails.getUsername()));
	}
//	@GetMapping("/current")
//	public ResponseEntity<User> getCurrentUser() {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if (principal instanceof UserDetails) {
//			username = ((UserDetails) principal).getUsername();
//		} else {
//			username = principal.toString();
//		}
//		User user = service.findByUsername(username);
//		return ResponseEntity.ok(user);
//	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new UserDTO(createdUser.getId(), createdUser.getUsername()));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody User user) {
		User updatedUser = userService.updateUser(userId, user);
		return ResponseEntity.ok(new UserDTO(updatedUser.getId(), updatedUser.getUsername()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TaskDTO> createTaskForUser(@PathVariable long userId, @RequestBody Task task){
		Task createdTask = userService.createTaskForUser(userId, task);
		TaskDTO dto = new TaskDTO(
				createdTask.getTaskid(),
				createdTask.getTasktitle(),
				createdTask.getTaskdescription(),
				createdTask.isTaskcompleted(),
				createdTask.getTaskcategory(),
				createdTask.getCreatedAt(),
				createdTask.getDueDate(),
				createdTask.getTaskpriority(),
				createdTask.getUser().getId()
				);
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}
	
}

