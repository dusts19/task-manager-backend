//package com.example.taskmanager.config;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class DataSourceConfig {
//	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		data
//	
//	
////
////	@Value("${spring.datasource.url}")
////	private String datasourceUrl;
////	
////	@Value("${spring.datasource.username}")
////	private String datasourceUsername;
////	
////	@Value("${spring.datasource.password-file}")
////	private String datasourcePasswordFile;
////	
////	
////	@Bean
////	@Primary
////	public DataSource dataSource() throws IOException {
////		String datasourcePassword = new String(Files.readAllBytes(Paths.get(datasourcePasswordFile)));
////		return DataSourceBuilder.create()
////				.url(datasourceUrl)
////				.username(datasourceUsername)
////				.password(datasourcePassword.trim())
////				.build();
//	}
//}
