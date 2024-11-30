package com.example.taskmanager.model;


import java.util.HashSet;
import java.util.Set;

import com.example.taskmanager.model.Task.Priority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RoleName name;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
		name = "role_permissions",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "permission_id")
	)
	private Set<Permission> permissions = new HashSet<>();
	
	public enum RoleName {
		USER,
		ADMIN,
		OWNER
	}
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();
}
