package com.example.taskmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.taskmanager.model.Task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private long id;
	private String username;
//	private String role;
	
//	public UserDTO() {}
//	
//	public UserDTO(Long id, String username) {
//		this.id = id;
//		this.username = username;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	@Override
//	public String toString() {
//		return "UserDTO [id=" + id + ", username=" + username + "]";
//	}
//	
}
