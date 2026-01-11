package com.example.taskmanager.dto;

import java.time.LocalDate;

import com.example.taskmanager.model.Task.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskUpdateDTO(
		String tasktitle,
		String taskdescription,
		String taskcategory,
		Priority taskpriority,
		LocalDate dueDate,
		Boolean taskcompleted
		) {
}
