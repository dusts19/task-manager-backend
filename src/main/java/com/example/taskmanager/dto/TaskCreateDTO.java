package com.example.taskmanager.dto;

import java.time.LocalDate;

import com.example.taskmanager.model.Task.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateDTO(
		@NotBlank(message = "Task Title is required")
		String tasktitle,
		@NotBlank(message = "Task description is required")
		String taskdescription,
		@NotBlank(message = "Task category is required")
		String taskcategory,
		@NotNull(message = "Due date is required")
		LocalDate dueDate,
		@NotNull(message = "Task priority is required")
		Priority taskpriority
		) {
}
