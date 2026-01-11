package com.example.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskmanager.dto.TaskCreateDTO;
import com.example.taskmanager.dto.TaskDetailsDTO;
import com.example.taskmanager.dto.TaskListDTO;
import com.example.taskmanager.dto.TaskUpdateDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://dailydirector.vercel.app"})
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService tService;
	
	private final UserService uService;
	
	public TaskController(TaskService tService, UserService uService) {
		this.tService = tService;
		this.uService = uService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<TaskListDTO>> getTasks() {
		return ResponseEntity.ok(tService.getAllTaskListDTOs());
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<TaskDetailsDTO> getTaskById(@PathVariable long taskId) {
		return ResponseEntity.ok(tService.getTaskById(taskId));
	}
	
	@GetMapping
	public ResponseEntity<List<TaskListDTO>> getTasksByUser(@AuthenticationPrincipal UserDetails userDetails) {
		User user = uService.getUserEntityByUsername(userDetails.getUsername());
		return ResponseEntity.ok(tService.getTasksByUser(user));
	}

	@GetMapping("/search")
	public ResponseEntity<List<Task>> getTasksByName(@RequestParam String taskTitle, @AuthenticationPrincipal UserDetails userDetails) {
		User user = uService.getUserEntityByUsername(userDetails.getUsername());
		List<Task> tasks = tService.getTasksByUserAndTitle(user, taskTitle);
		return ResponseEntity.ok(tasks);
	}
	
	@PostMapping
	public ResponseEntity<TaskDetailsDTO> createTask(@RequestBody TaskCreateDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {

		
		TaskDetailsDTO createdTaskDTO = tService.createTask(taskDTO, userDetails.getUsername());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
	}
	@PatchMapping("/{taskId}")
	public ResponseEntity<TaskDetailsDTO> updateTask(@PathVariable long taskId, @RequestBody TaskUpdateDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
		TaskDetailsDTO updatedTaskDTO = tService.updateTask(taskId, taskDTO, userDetails.getUsername());
		return ResponseEntity.ok(updatedTaskDTO);
	}
//	@PutMapping("/{taskId}")
//	public ResponseEntity<TaskDTO> updateTask(@PathVariable long taskId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
//		if (taskDTO.getTasktitle() == null || taskDTO.getTaskdescription() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		
////		User user = uService.getUserById(task.getUserid());
//		User user = uService.findByUsername(userDetails.getUsername());//
//		Task existingTask = tService.getTaskById(taskId);
//		if (existingTask == null || existingTask.getUser() == null || existingTask.getUser().getId() != user.getId()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//		}
//		
//		existingTask.setTasktitle(taskDTO.getTasktitle());
//		existingTask.setTaskdescription(taskDTO.getTaskdescription());
//		existingTask.setTaskcompleted(taskDTO.isTaskcompleted());
//		existingTask.setTaskcategory(taskDTO.getTaskcategory());
//		existingTask.setDueDate(taskDTO.getDueDate());
//		existingTask.setTaskpriority(taskDTO.getTaskpriority());
//		
//		Task updatedTask = tService.updateTask(existingTask, user.getUsername());
//		
//		TaskDTO updatedTaskDTO = new TaskDTO(
//				updatedTask.getTaskid(),
//				updatedTask.getTasktitle(),
//				updatedTask.getTaskdescription(),
//				updatedTask.isTaskcompleted(),
//				updatedTask.getTaskcategory(),
//				updatedTask.getCreatedAt(),
//				updatedTask.getDueDate(),
//				updatedTask.getTaskpriority(),
//				updatedTask.getUser().getId()
//		);
//		
//		return ResponseEntity.ok(updatedTaskDTO);
//	}

	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable long taskId) {
		tService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{taskId}/priority")
	public ResponseEntity<TaskListDTO> updateTaskPriority(@PathVariable long id, @RequestParam Task.Priority priority) {
		return ResponseEntity.ok(
				tService.updateTaskPriority(id, priority)
		);
	}
	
}
