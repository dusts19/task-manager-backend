package com.example.taskmanager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.JwtResponse;
import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.model.User;
//import com.example.taskmanager.model.User.Role;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.util.JwtUtil;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://dailydirector.vercel.app"})
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserService service;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> registerUser(@RequestBody User user) {
		User createdUser = service.createUser(user);
		String token = jwtUtil.generateToken(createdUser.getUsername());
		
		System.out.println("User roles: " + createdUser.getRoles());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new JwtResponse(token));
	}
	
//	
//	@PostMapping("/register")
//	public ResponseEntity<JwtResponse> registerUser(@RequestBody User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		User createdUser = service.createUser(user);
//		String token = jwtUtil.generateToken(createdUser.getUsername());
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//	
//	@PostMapping("/register")
//	public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		User createdUser = service.createUser(user);
//		Map<String, Object> response = new HashMap<>();
//		response.put("user", createdUser);
//		return ResponseEntity.ok(response);
//	}
//	

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest loginRequest){
		User user = service.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
		String token = jwtUtil.generateToken(user.getUsername());
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
//	
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest loginRequest){
//		User user = service.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
//		String token = jwtUtil.generateToken(user.getUsername());
//		Map<String, String> response = new HashMap<>();
//		response.put("token", token);
//		return ResponseEntity.ok(response);
//	}
//	
}
