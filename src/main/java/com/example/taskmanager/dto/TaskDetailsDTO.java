package com.example.taskmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.taskmanager.model.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDetailsDTO {
	private long taskid;
	private String tasktitle;
	private String taskdescription;
	private boolean taskcompleted;
	private String taskcategory;
	private LocalDateTime createdAt;
	private LocalDate dueDate;
	private Task.Priority taskpriority;
	private long userid;
}
