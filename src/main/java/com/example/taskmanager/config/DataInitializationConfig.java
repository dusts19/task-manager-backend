//package com.example.taskmanager.config;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import java.util.logging.Logger;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
////import org.springframework.transaction.annotation.Transactional;
//
//import com.example.taskmanager.model.Permission;
//import com.example.taskmanager.model.Role;
//import com.example.taskmanager.model.User;
//import com.example.taskmanager.repository.PermissionRepo;
//import com.example.taskmanager.repository.RoleRepo;
//import com.example.taskmanager.repository.UserRepo;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
//
////@Component
////public class DataInitializationConfig implements CommandLineRunner {
//	
//
//@Configuration
//public class DataInitializationConfig{
//
//	private static final Logger LOGGER = Logger.getLogger(DataInitializationConfig.class.getName());
//	
//	@Autowired
//	private UserRepo userRepo;
//	
//	@Autowired
//	private RoleRepo roleRepo;
//	
//	@Autowired
//	private PermissionRepo permissionRepo;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@PersistenceContext
//	private EntityManager entityManager;
//	
////	@Bean
////	@Transactional
////	public CommandLineRunner dataInitializer() {
////		return args -> {
////			LOGGER.info("Initializating data...");
//////		User adminUser = userRepo.findByUsername("admin");
////			createAdminUser();
////			
////		};
////	}
//
//
////	@Override
////	public void run (String...args) throws Exception{
////			LOGGER.info("Initializating data...");
//////		User adminUser = userRepo.findByUsername("admin");
////			try {
////				createAdminUser();				
////			} catch (Exception e) {
////				LOGGER.severe("Exception during data initialization: " + e.getMessage());
////				e.printStackTrace();
////				throw e;
////			}
////	}
//	
//	@PostConstruct
//	public void dataInitializer() {
//			LOGGER.info("Initializating data...");
////		User adminUser = userRepo.findByUsername("admin");
//			try {
//				createAdminUser();				
//			} catch (Exception e) {
//				LOGGER.severe("Exception during data initialization: " + e.getMessage());
//				e.printStackTrace();
//				throw e;
//			}
//			
//	}
//	
//	@Transactional
//	public void createAdminUser() {
//		Optional<User> optionalAdminUser = userRepo.findByUsername("admin");
//		if (optionalAdminUser.isEmpty()) {
//			LOGGER.info("Admin user does not exist. Creating admin user...");
//			
//			Permission readPermission = new Permission();
//			readPermission.setName("READ");
////			entityManager.persist(readPermission);
//			readPermission = permissionRepo.save(readPermission);
//			
//			Permission writePermission = new Permission();
//			writePermission.setName("WRITE");
////			entityManager.persist(writePermission);
//			writePermission = permissionRepo.save(writePermission);
//			LOGGER.info("Permissions 'READ' and 'WRITE' created.");
//			
//			Role adminRole = roleRepo.findByName(Role.RoleName.ADMIN);
//			if (adminRole == null) {
//				adminRole = new Role();
//				adminRole.setName(Role.RoleName.ADMIN);
////				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
//				Set<Permission> adminPermissions = new HashSet<>();
//				adminPermissions.add(readPermission);
//				adminPermissions.add(writePermission);
//				adminRole.setPermissions(adminPermissions);
////				entityManager.persist(adminRole);
//				adminRole = roleRepo.save(adminRole);
//				LOGGER.info("Role 'ADMIN' created and permissions assigned.");
//			} else {
//				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
////				entityManager.merge(adminRole);
//				adminRole = roleRepo.save(adminRole);
////				adminRole = roleRepo.findById(adminRole.getId()).orElseThrow(() -> new RuntimeException("Failed to retrieve saved admin role"));
//			}
////			adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
////			roleRepo.save(adminRole);
//			
////			adminRole = roleRepo.findById(adminRole.getId()).orElseThrow(() -> new RuntimeException("Failed to retrieve saved admin role"));
////			Hibernate.initialize(adminRole.getPermissions());
//			
//			User adminUser = new User();
//			adminUser.setUsername("admin");
//			adminUser.setPassword(passwordEncoder.encode("pass1234"));
//			adminUser.setEmail("admin@example.com");
//			LOGGER.info("Admin user set name, pw, and email");
//			
//			Set<Role> roles = new HashSet<>();
//			LOGGER.info("Roles hashset created.");
//			roles.add(adminRole);
//			LOGGER.info("Admin user role added.");
//			adminUser.setRoles(roles);
//			
//			LOGGER.info("Admin user role set: " + roles);
////			entityManager.persist(adminUser);
//			userRepo.save(adminUser);
//			LOGGER.info("Admin user created: username='admin', password='pass1234'.");
//			
//			System.out.println("Admin user created: username='admin', password='pass1234'");
//		} else {
//			LOGGER.info("Admin user already exists.");
//		}
//	}
////	@PostConstruct
////	@Transactional
////	public void init() {
////		LOGGER.info("Initializating data...");
//////		User adminUser = userRepo.findByUsername("admin");
////		Optional<User> optionalAdminUser = userRepo.findByUsername("admin");
////		if (optionalAdminUser.isEmpty()) {
////			LOGGER.info("Admin user does not exist. Creating admin user...");
////			
////			Permission readPermission = new Permission();
////			readPermission.setName("READ");
////			permissionRepo.save(readPermission);
////			
////			Permission writePermission = new Permission();
////			writePermission.setName("WRITE");
////			permissionRepo.save(writePermission);
////			LOGGER.info("Permissions 'READ' and 'WRITE' created.");
////			
////			Role adminRole = roleRepo.findByName(Role.RoleName.ADMIN);
////			if (adminRole == null) {
////				adminRole = new Role();
////				adminRole.setName(Role.RoleName.ADMIN);
////				Set<Permission> adminPermissions = new HashSet<>();
////				adminPermissions.add(readPermission);
////				adminPermissions.add(writePermission);
////				adminRole.setPermissions(adminPermissions);
////				adminRole = roleRepo.saveAndFlush(adminRole);
////				LOGGER.info("Role 'ADMIN' created and permissions assigned.");
////			}
////			
//////			adminRole = roleRepo.findById(adminRole.getId()).orElseThrow(() -> new RuntimeException("Failed to retrieve saved admin role"));
////			
////			User adminUser = new User();
////			adminUser.setUsername("admin");
////			adminUser.setPassword(passwordEncoder.encode("pass1234"));
////			adminUser.setEmail("admin@example.com");
////			Set<Role> roles = new HashSet<>();
////			roles.add(adminRole);
////			adminUser.setRoles(roles);
////			userRepo.save(adminUser);
////			LOGGER.info("Admin user created: username='admin', password='pass1234'.");
////			
////			System.out.println("Admin user created: username='admin', password='pass1234'");
////		} else {
////			LOGGER.info("Admin user already exists.");
////		}
////	}
//}
