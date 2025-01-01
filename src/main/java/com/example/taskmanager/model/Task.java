package com.example.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskid;
	
	@Column(nullable = false)
	private String tasktitle;
	
	@Column(nullable = false)
	private String taskdescription;
	
	@Column(nullable = false)
	private boolean taskcompleted;
	
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Priority taskpriority;
	
	public enum Priority {
		LOW, MEDIUM, HIGH
	}
	
}
