package com.example.taskmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.taskmanager.model.Task;

public record TaskListDTO(
		long taskid,
		String tasktitle,
		String taskdescription,
		Task.Priority taskpriority,
		String taskcategory,
		LocalDate dueDate,
		boolean taskcompleted
		) {}
