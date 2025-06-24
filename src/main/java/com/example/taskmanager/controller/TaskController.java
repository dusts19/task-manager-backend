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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.service.TaskService;
import com.example.taskmanager.service.UserService;



@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://taskmanager.vercel.app"})
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService tService;
	
	private final UserService uService;
	
	public TaskController(TaskService tService, UserService uService) {
		this.tService = tService;
		this.uService = uService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Task>> getTasks() {
		List<Task> tasks = tService.getTasks();
		return ResponseEntity.ok(tasks);
	}
	
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getTasksByUser(@AuthenticationPrincipal UserDetails userDetails) {
		User user = uService.findByUsername(userDetails.getUsername());
		List<TaskDTO> tasks = tService.getTasksByUser(user);
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable long taskId) {
		Task task = tService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}
	
//	@PostMapping
//	public ResponseEntity<Task> addTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
//		if (task.getTasktitle() == null || task.getTaskdescription() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		Task createdTask = tService.addTask(task, userDetails.getUsername());
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//	}

	@PostMapping
	public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
		if (taskDTO.getTasktitle() == null || taskDTO.getTaskdescription() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
//		User user = uService.getUserById(taskDTO.getUserid());
		User user = uService.findByUsername(userDetails.getUsername());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Task task = new Task();
		task.setTasktitle(taskDTO.getTasktitle());
		task.setTaskdescription(taskDTO.getTaskdescription());
		task.setTaskcategory(taskDTO.getTaskcategory());
		task.setDueDate(taskDTO.getDueDate());
		task.setTaskpriority(taskDTO.getTaskpriority());
		task.setTaskcompleted(taskDTO.isTaskcompleted());
		task.setUser(user);
		
		Task createdTask = tService.addTask(task);
		
		TaskDTO createdTaskDTO = new TaskDTO();
		createdTaskDTO.setTaskid(createdTask.getTaskid());
		createdTaskDTO.setTasktitle(createdTask.getTasktitle());
		createdTaskDTO.setTaskdescription(createdTask.getTaskdescription());
		createdTaskDTO.setTaskcategory(createdTask.getTaskcategory());
		createdTaskDTO.setDueDate(createdTask.getDueDate());
		createdTaskDTO.setTaskpriority(createdTask.getTaskpriority());
		createdTaskDTO.setTaskcompleted(createdTask.isTaskcompleted());
		createdTaskDTO.setUserid(createdTask.getUser().getId());
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
	}

//	@PutMapping("/{taskId}")
//	public ResponseEntity<Task> updateTask(@PathVariable long taskId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
//		if (taskDTO.getTasktitle() == null || taskDTO.getTaskdescription() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		
////		User user = uService.getUserById(task.getUserid());
//		User user = uService.findByUsername(userDetails.getUsername());
//		if (user == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		Task task = new Task();
//		task.setTaskid(taskId);
//		task.setTasktitle(taskDTO.getTasktitle());
//		task.setTaskdescription(taskDTO.getTaskdescription());
//		task.setTaskcompleted(taskDTO.isTaskcompleted());
//		task.setTaskpriority(taskDTO.getTaskpriority());
//		task.setUser(user);
//		
//		Task updatedTask = tService.updateTask(task, userDetails.getUsername());
//		
//		return ResponseEntity.ok(updatedTask);
//	}

	@PutMapping("/{taskId}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable long taskId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
		if (taskDTO.getTasktitle() == null || taskDTO.getTaskdescription() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
//		User user = uService.getUserById(task.getUserid());
		User user = uService.findByUsername(userDetails.getUsername());
		Task existingTask = tService.getTaskById(taskId);
		if (existingTask == null || existingTask.getUser() == null || existingTask.getUser().getId() != user.getId()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		existingTask.setTasktitle(taskDTO.getTasktitle());
		existingTask.setTaskdescription(taskDTO.getTaskdescription());
		existingTask.setTaskcompleted(taskDTO.isTaskcompleted());
		existingTask.setTaskcategory(taskDTO.getTaskcategory());
		existingTask.setDueDate(taskDTO.getDueDate());
		existingTask.setTaskpriority(taskDTO.getTaskpriority());
		
		Task updatedTask = tService.updateTask(existingTask, user.getUsername());
		
		TaskDTO updatedTaskDTO = new TaskDTO(
				updatedTask.getTaskid(),
				updatedTask.getTasktitle(),
				updatedTask.getTaskdescription(),
				updatedTask.isTaskcompleted(),
				updatedTask.getTaskcategory(),
				updatedTask.getCreatedAt(),
				updatedTask.getDueDate(),
				updatedTask.getTaskpriority(),
				updatedTask.getUser().getId()
		);
		
		return ResponseEntity.ok(updatedTaskDTO);
	}

//	@PutMapping("/{taskId}")
//	public ResponseEntity<TaskDTO> updateTask(@PathVariable long taskId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal UserDetails userDetails) {
//		if (taskDTO.getTasktitle() == null || taskDTO.getTaskdescription() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		
////		User user = uService.getUserById(task.getUserid());
//		User user = uService.findByUsername(userDetails.getUsername());
//
//		
//		
//		Task task = new Task();
//		task.setTaskid(taskId);
//		task.setTasktitle(taskDTO.getTasktitle());
//		task.setTaskdescription(taskDTO.getTaskdescription());
//		task.setTaskcompleted(taskDTO.isTaskcompleted());
//		task.setTaskcategory(taskDTO.getTaskcategory());
//		task.setCreatedAt(taskDTO.getCreatedAt());
//		task.setDueDate(taskDTO.getDueDate());
//		task.setTaskpriority(taskDTO.getTaskpriority());
//		task.setUser(user);
//		
//		Task updatedTask = tService.updateTask(task, userDetails.getUsername());
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
	
	
//	@PutMapping
//	public ResponseEntity<Task> updateTask(@RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
//		if (task.getTasktitle() == null || task.getTaskdescription() == null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		Task updatedTask = tService.updateTask(task, userDetails.getUsername());
//		return ResponseEntity.ok(updatedTask);
//	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable long taskId) {
		tService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{taskId}/priority")
	public ResponseEntity<Task> updateTaskPriority(@PathVariable long id, @RequestParam Task.Priority priority) {
		Task updatedTask = tService.updateTaskPriority(id, priority);
		return ResponseEntity.ok(updatedTask);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Task>> getTasksByName(@RequestParam String taskTitle, @AuthenticationPrincipal UserDetails userDetails) {
		User user = uService.findByUsername(userDetails.getUsername());
		List<Task> tasks = tService.getTasksByUserAndTitle(user, taskTitle);
		return ResponseEntity.ok(tasks);
	}
	
}
