package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	Optional<Role> findByName(Role.RoleName name);
}
//
//and my project folder looks like src/main/java/com.example.taskmanager and their folders below, where would i put this DataInitializer file? 
//src/main/java/com.example.taskmanager.config/
//src/main/java/com.example.taskmanager.controller/
//src/main/java/com.example.taskmanager.dto/
//src/main/java/com.example.taskmanager.filter/
//src/main/java/com.example.taskmanager.model/
//src/main/java/com.example.taskmanager.repository/
//src/main/java/com.example.taskmanager.service/
//src/main/java/com.example.taskmanager.util/
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PermissionRepository permissionRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Create default permissions
//        Permission readPermission = new Permission();
//        readPermission.setName("READ");
//        permissionRepository.save(readPermission);
//
//        Permission writePermission = new Permission();
//        writePermission.setName("WRITE");
//        permissionRepository.save(writePermission);
//
//        // Create USER role with default permissions
//        Role userRole = new Role();
//        userRole.setName(Role.RoleName.USER);
//        userRole.getPermissions().add(readPermission);
//        roleRepository.save(userRole);
//
//        // Create ADMIN role with more permissions
//        Role adminRole = new Role();
//        adminRole.setName(Role.RoleName.ADMIN);
//        adminRole.getPermissions().add(readPermission);
//        adminRole.getPermissions().add(writePermission);
//        roleRepository.save(adminRole);
//    }
//}
