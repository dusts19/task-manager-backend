package com.example.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
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
	private String tasktitle;
	private String taskdescription;
	private boolean taskcompleted;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	private Priority taskpriority;
	
	public enum Priority {
		LOW, MEDIUM, HIGH
	}
	
}
