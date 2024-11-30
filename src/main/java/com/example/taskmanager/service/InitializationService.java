//package com.example.taskmanager.service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import java.util.logging.Logger;
//
//import org.hibernate.Hibernate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.support.TransactionTemplate;
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
//@Service
//public class InitializationService {
//	
//	private static final Logger LOGGER = Logger.getLogger(InitializationService.class.getName());
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
//	@Autowired
//	private TransactionTemplate transactionTemplate;
//	
//	@PostConstruct
//	@Transactional
//	public void init() {
//		try {
//			initializeBasicUser();
//			initializeAdminUser();
//		}catch(Exception e) {
//			LOGGER.severe("Exception during data initialization: " + e.getMessage());
//			e.printStackTrace();
//			throw e;
//		}
//		LOGGER.info("Data init completed.");
//	}
//	
//
//	@Transactional
//	public void initializeBasicUser() {
//		LOGGER.info("Initializing Basic User...");
//		transactionTemplate.execute(status -> {
//			Permission readPermission = permissionRepo.findByName("READ").orElseGet(() -> {
//				Permission permission = new Permission();
//				permission.setName("READ");
////				entityManager.persist(permission);
////				return permission;
//				return permissionRepo.save(permission);
//			});
//		
//			Role userRole = roleRepo.findByName(Role.RoleName.USER).orElseGet(() -> {
//				Role role = new Role();
//				role.setName(Role.RoleName.USER);
//				Set<Permission> userPermissions = new HashSet<>();
//				userPermissions.add(readPermission);
//				role.setPermissions(userPermissions);
////				entityManager.persist(role);
////				return role;
//				return roleRepo.save(role);
//			});
//			LOGGER.info("Basic User initialization completed.");
//			return null;
//		});
//		
//
////		entityManager.flush();
////		Hibernate.initialize(userRole.getPermissions());
//	}
//	
//
//	@Transactional
//	public void initializeAdminUser() {
//		LOGGER.info("Initializing Admin User...");
//		transactionTemplate.execute(status -> {
//			Optional<User> optionalAdminUser = userRepo.findByUsername("admin");
//			if (optionalAdminUser.isEmpty()) {
//				LOGGER.info("Admin user does not exist. Creating admin user...");
//				
//				Permission readPermission = permissionRepo.findByName("READ").orElseGet(() -> {
//					Permission permission = new Permission();
//					permission.setName("READ");
////					entityManager.persist(permission);
////					return permission;
//					return permissionRepo.save(permission);
//				});
//				Permission writePermission = permissionRepo.findByName("WRITE").orElseGet(() -> {
//					Permission permission = new Permission();
//					permission.setName("WRITE");
////					entityManager.persist(permission);
////					return permission;
//					return permissionRepo.save(permission);
//				});
//				LOGGER.info("Permissions 'READ' and 'WRITE' created.");
//				
//				Role adminRole = roleRepo.findByName(Role.RoleName.ADMIN).orElseGet(() -> {
//					Role role = new Role();
//					role.setName(Role.RoleName.ADMIN);
//					Set<Permission> adminPermissions = new HashSet<>();
//					adminPermissions.add(readPermission);
//					adminPermissions.add(writePermission);
//					role.setPermissions(adminPermissions);
////					entityManager.persist(role);
////					return role;
//					return roleRepo.save(role);
//				});
//				LOGGER.info("Role 'ADMIN' created and permissions assigned.");
////			if (adminRole == null) {
////			} else {
////				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
////				adminRole = roleRepo.save(adminRole);
////			}
//				
////				entityManager.flush();
////			Hibernate.initialize(adminRole.getPermissions());
//				
//				User adminUser = new User();
//				adminUser.setUsername("admin");
//				adminUser.setPassword(passwordEncoder.encode("pass1234"));
//				adminUser.setEmail("admin@example.com");
//				LOGGER.info("Admin user set name, pw, and email");
//				
//				adminUser.setRoles(Set.of(adminRole));
//				
////			Set<Role> roles = new HashSet<>();
////			LOGGER.info("Roles hashset created.");
////			roles.add(adminRole);
////			LOGGER.info("Admin user role added.");
////			adminUser.setRoles(roles);
//				
//				LOGGER.info("Admin user role set: " + adminRole);
////				entityManager.persist(adminUser);
//				userRepo.save(adminUser);
//				LOGGER.info("Admin user created: username='admin', password='pass1234'.");
//				
//				System.out.println("Admin user created: username='admin', password='pass1234'");
//			} else {
//				LOGGER.info("Admin user already exists.");
//			}
//			return null;
//		});
//	}
//	
//	
////	@Transactional
////	public void initializeBasicUser() {
////
////		Permission readPermission = new Permission();
////		readPermission.setName("READ");
////		permissionRepo.save(readPermission);
////		
////		Role userRole = new Role();
////		userRole.setName(Role.RoleName.USER);
////		Set<Permission> userPermissions = new HashSet<>();
////		userPermissions.add(readPermission);
////		userRole.setPermissions(userPermissions);
////		roleRepo.save(userRole);
////		
//////	}
//	
//	
////	@Transactional
////	public void initializeBasicUser() {
////		Permission readPermission = permissionRepo.findByName("READ").orElseGet(() -> {
////			Permission permission = new Permission();
//////			permission.setName("READ");
////			entityManager.persist(permission);
////			return permission;
//////			return permissionRepo.save(permission);
////		});
////		
////		Role userRole = roleRepo.findByName(Role.RoleName.USER).orElseGet(() -> {
////			Role role = new Role();
////			role.setName(Role.RoleName.USER);
////			Set<Permission> userPermissions = new HashSet<>();
////			userPermissions.add(readPermission);
////			role.setPermissions(userPermissions);
////			entityManager.persist(role);
////			return role;
//////			return roleRepo.save(role);
////		});
////
////		entityManager.flush();
//////		Hibernate.initialize(userRole.getPermissions());
////	}
////	
////
////	@Transactional
////	public void initializeAdminUser() {
////		Optional<User> optionalAdminUser = userRepo.findByUsername("admin");
////		if (optionalAdminUser.isEmpty()) {
////			LOGGER.info("Admin user does not exist. Creating admin user...");
////			
////			Permission readPermission = permissionRepo.findByName("READ").orElseGet(() -> {
////				Permission permission = new Permission();
////				permission.setName("READ");
////				entityManager.persist(permission);
////				return permission;
//////				return permissionRepo.save(permission);
////			});
////			Permission writePermission = permissionRepo.findByName("WRITE").orElseGet(() -> {
////				Permission permission = new Permission();
////				permission.setName("WRITE");
////				entityManager.persist(permission);
////				return permission;
//////				return permissionRepo.save(permission);
////			});
////			LOGGER.info("Permissions 'READ' and 'WRITE' created.");
////			
////			Role adminRole = roleRepo.findByName(Role.RoleName.ADMIN).orElseGet(() -> {
////				Role role = new Role();
////				role.setName(Role.RoleName.ADMIN);
////				Set<Permission> adminPermissions = new HashSet<>();
////				adminPermissions.add(readPermission);
////				adminPermissions.add(writePermission);
////				role.setPermissions(adminPermissions);
////				entityManager.persist(role);
////				return role;
//////				return roleRepo.save(role);
////			});
////			LOGGER.info("Role 'ADMIN' created and permissions assigned.");
//////			if (adminRole == null) {
//////			} else {
//////				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
//////				adminRole = roleRepo.save(adminRole);
//////			}
////			
////			entityManager.flush();
//////			Hibernate.initialize(adminRole.getPermissions());
////			
////			User adminUser = new User();
////			adminUser.setUsername("admin");
////			adminUser.setPassword(passwordEncoder.encode("pass1234"));
////			adminUser.setEmail("admin@example.com");
////			LOGGER.info("Admin user set name, pw, and email");
////			
////			adminUser.setRoles(Set.of(adminRole));
////			
//////			Set<Role> roles = new HashSet<>();
//////			LOGGER.info("Roles hashset created.");
//////			roles.add(adminRole);
//////			LOGGER.info("Admin user role added.");
//////			adminUser.setRoles(roles);
////			
////			LOGGER.info("Admin user role set: " + adminRole);
////			entityManager.persist(adminUser);
//////			userRepo.save(adminUser);
////			LOGGER.info("Admin user created: username='admin', password='pass1234'.");
////			
////			System.out.println("Admin user created: username='admin', password='pass1234'");
////		} else {
////			LOGGER.info("Admin user already exists.");
////		}
////	}
//
//	
////	@Transactional
////	public void initializeAdminUser() {
////		Optional<User> optionalAdminUser = userRepo.findByUsername("admin");
////		if (optionalAdminUser.isEmpty()) {
////			LOGGER.info("Admin user does not exist. Creating admin user...");
////			
////			Permission readPermission = new Permission();
////			readPermission.setName("READ");
//////			entityManager.persist(readPermission);
////			readPermission = permissionRepo.save(readPermission);
////			
////			Permission writePermission = new Permission();
////			writePermission.setName("WRITE");
//////			entityManager.persist(writePermission);
////			writePermission = permissionRepo.save(writePermission);
////			LOGGER.info("Permissions 'READ' and 'WRITE' created.");
////			
////			Role adminRole = roleRepo.findByName(Role.RoleName.ADMIN);
////			if (adminRole == null) {
////				adminRole = new Role();
////				adminRole.setName(Role.RoleName.ADMIN);
//////				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
////				Set<Permission> adminPermissions = new HashSet<>();
////				adminPermissions.add(readPermission);
////				adminPermissions.add(writePermission);
////				adminRole.setPermissions(adminPermissions);
//////				entityManager.persist(adminRole);
////				adminRole = roleRepo.save(adminRole);
////				LOGGER.info("Role 'ADMIN' created and permissions assigned.");
////			} else {
////				adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
//////				entityManager.merge(adminRole);
////				adminRole = roleRepo.save(adminRole);
//////				adminRole = roleRepo.findById(adminRole.getId()).orElseThrow(() -> new RuntimeException("Failed to retrieve saved admin role"));
////			}
//////			adminRole.setPermissions(new HashSet<>(Set.of(readPermission, writePermission)));
//////			roleRepo.save(adminRole);
////			
//////			adminRole = roleRepo.findById(adminRole.getId()).orElseThrow(() -> new RuntimeException("Failed to retrieve saved admin role"));
//////			Hibernate.initialize(adminRole.getPermissions());
////			
////			User adminUser = new User();
////			adminUser.setUsername("admin");
////			adminUser.setPassword(passwordEncoder.encode("pass1234"));
////			adminUser.setEmail("admin@example.com");
////			LOGGER.info("Admin user set name, pw, and email");
////			
////			Set<Role> roles = new HashSet<>();
////			LOGGER.info("Roles hashset created.");
////			roles.add(adminRole);
////			LOGGER.info("Admin user role added.");
////			adminUser.setRoles(roles);
////			
////			LOGGER.info("Admin user role set: " + roles);
//////			entityManager.persist(adminUser);
////			userRepo.save(adminUser);
////			LOGGER.info("Admin user created: username='admin', password='pass1234'.");
////			
////			System.out.println("Admin user created: username='admin', password='pass1234'");
////		} else {
////			LOGGER.info("Admin user already exists.");
////		}
////	}
//}
