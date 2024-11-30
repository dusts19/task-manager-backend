package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Permission;

@Repository
public interface PermissionRepo extends JpaRepository<Permission, Long>{
	Optional<Permission> findByName(String name);
}
//~[spring-boot-devtools-3.3.3.jar:3.3.3]
//Caused by: org.postgresql.util.PSQLException: ERROR: insert or update on table "user_roles" violates foreign key constraint "fk6fql8djp64yp4q9b3qeyhr82b"
//  Detail: Key (user_id)=(1) is not present in table "user_app".