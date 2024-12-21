package com.example.taskmanager.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	@Value("${LOCAL_DB_URL}")
	private String localDbUrl;
	
	@Value("${SPRING_DATASOURCE_URL}")
	private String dockerDbUrl;
	

//	@Value("${SPRING_DATASOURCE_URL}")
//	private String localDbUrl;
//	
//	@Value("${SPRING_DATASOURCE_URL}")
//	private String dockerDbUrl;
	
	@Value("${DB_USERNAME}")
	private String dbUsername;
	
	@Value("${DB_PASSWORD}")
	private String dbPassword;
	
	@Value("${SPRING_PROFILES_ACTIVE:local}")
	private String activeProfile;
	
	public String getLocalDbUrl() {
		return localDbUrl;
	}
	public void setLocalDbUrl(String localDbUrl) {
		this.localDbUrl = localDbUrl;
	}
	public String getDockerDbUrl() {
		return dockerDbUrl;
	}
	public void setDockerDbUrl(String dockerDbUrl) {
		this.dockerDbUrl = dockerDbUrl;
	}
	
	public String getDbUsername() {
		return dbUsername;
	}
	
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	
	public String getDbPassword() {
		return dbPassword;
	}
	
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		if ("docker".equals(activeProfile)) {
			dataSource.setUrl(dockerDbUrl);
		} else {
			dataSource.setUrl(localDbUrl);
		}
		
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		
		return dataSource;
	}
}
