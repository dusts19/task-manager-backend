package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.taskmanager.filter.JwtFilter;
import com.example.taskmanager.util.JwtUtil;

@Configuration
public class JwtFilterConfig {
	
	@Bean
	public JwtFilter jwtFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService) {
		return new JwtFilter(jwtUtil, userDetailsService);
	}
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil();
	}
}
