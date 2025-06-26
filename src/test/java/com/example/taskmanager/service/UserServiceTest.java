package com.example.taskmanager.service;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepo userRepo;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void getUsers_returnsUserList() {
		User user1 = User.builder()
				.username("Check")
				.password("pass1")
				.email("check@email.com")
				.build();
		User user2 = User.builder()
				.username("Bob")
				.password("pass2")
				.email("bob@email.com")
				.build();
		
		when(userRepo.findAll()).thenReturn(Arrays.asList(user1, user2));
		
		List<User> result = userService.getUsers();
		
		// Using JUnit
//		assertEquals(2, result.size());
//		assertEquals("Check", result.get(0).getUsername());
//		assertEquals("check@email.com", result.get(0).getEmail());
		
		assertThat(result).hasSize(2);
		assertThat(result.get(0).getUsername()).isEqualTo("Check");
		assertThat(result.get(0).getEmail()).isEqualTo("check@email.com");
	}
	
}