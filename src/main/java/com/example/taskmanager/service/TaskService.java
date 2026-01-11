package com.example.taskmanager.service;


import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.dto.TaskCreateDTO;
import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.dto.TaskDetailsDTO;
import com.example.taskmanager.dto.TaskListDTO;
import com.example.taskmanager.dto.TaskUpdateDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
	
	private final TaskRepo repo;
	
	private final UserService userService;
	
	public TaskService(TaskRepo repo, UserService userService) {
		this.repo = repo;
		this.userService = userService;
	}
//	@Transactional
//	public List<Task> getTasksByUser(User user) {
//		return repo.findByUser(user);
//	}
	

	@Transactional(readOnly = true)
	public List<TaskListDTO> getAllTaskListDTOs(){
		return repo.findAll().stream()
				.map(this::mapToTaskListDTO)
				.toList();
	}
	
	@Transactional(readOnly = true)
	public List<TaskListDTO> getTasksByUser(User user) {
		List<Task> tasks = repo.findByUser(user);
		return tasks.stream()
				.map(this::mapToTaskListDTO)
				.toList();
//				.collect(Collectors.toList()); // Older, mutable, Java 8+, slower than ^
	}

	@Transactional(readOnly = true)
	public TaskDetailsDTO getTaskById(long taskId) {
		Task task = repo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
		return mapToTaskDetailsDTO(task);
	}
	
	public List<Task> getTasksByUserAndTitle(User user, String tasktitle){
		return repo.findByUserAndTasktitleContaining(user, tasktitle);
	}


//	public Task createTask(Task task) {
//		if (task.getCreatedAt() == null) {
//			task.setCreatedAt(LocalDateTime.now());
//		}
//		if (task.getDueDate() == null) {
//			task.setDueDate(LocalDate.now().plusDays(7));
//		}
//		return repo.save(task);
//	}
	

	@Transactional
	public TaskDetailsDTO createTask(TaskCreateDTO taskDTO, String username) {
		User user = userService.getUserEntityByUsername(username);
		
		Task task = new Task();
		task.setTasktitle(taskDTO.tasktitle());
		task.setTaskdescription(taskDTO.taskdescription());
		task.setTaskcompleted(false);
		task.setTaskcategory(taskDTO.taskcategory());
		task.setCreatedAt(LocalDateTime.now());
		task.setDueDate(taskDTO.dueDate() != null ? taskDTO.dueDate() : LocalDate.now().plusDays(7));
		task.setTaskpriority(taskDTO.taskpriority());
		task.setUser(user);
		
		Task savedTask = repo.save(task);
		return mapToTaskDetailsDTO(savedTask);
	}
	
	
//	public Task addTask(Task task, String username) {
//		User user = userService.findByUsername(username);
//		task.setUser(user);
//		return repo.save(task);
//	}

	@Transactional
	public TaskDetailsDTO updateTask(long taskId, TaskUpdateDTO updatedTaskDTO, String username) {
		Task existingTask = repo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
		
		if (!existingTask.getUser().getUsername().equals(username)) {
			throw new RuntimeException("User not allowed to update this task");
		}
		
		existingTask.setTasktitle(updatedTaskDTO.tasktitle());
		existingTask.setTaskdescription(updatedTaskDTO.taskdescription());
		existingTask.setTaskcategory(updatedTaskDTO.taskcategory());
		existingTask.setDueDate(updatedTaskDTO.dueDate());
		existingTask.setTaskpriority(updatedTaskDTO.taskpriority());
		existingTask.setTaskcompleted(updatedTaskDTO.taskcompleted());
		
		Task updated = repo.save(existingTask);
		
		return mapToTaskDetailsDTO(updated);
	}
	
//	public Task updateTask(Task updatedTask, String username) {
//		Task existingTask = getTaskById(updatedTask.getTaskid());
//		if (existingTask == null) {
//			throw new RuntimeException("Task not found");
//		}
//		
//		existingTask.setTasktitle(updatedTask.getTasktitle());
//		existingTask.setTaskdescription(updatedTask.getTaskdescription());
//		existingTask.setTaskcompleted(updatedTask.isTaskcompleted());
//		existingTask.setTaskcategory(updatedTask.getTaskcategory());
//		existingTask.setDueDate(updatedTask.getDueDate());
//		existingTask.setTaskpriority(updatedTask.getTaskpriority());
//		
//		User user = userService.findByUsername(username);
//		existingTask.setUser(user);
//		
//		return repo.save(existingTask);
//	}


	
	
//	public Task updateTask(Task task, String username) {
//		User user = userService.findByUsername(username);
//		task.setUser(user);
//		return repo.save(task);
//	}
	
	public void deleteTask(long taskId) {
		repo.deleteById(taskId);
	}
	
	public TaskListDTO updateTaskPriority(long id, Task.Priority priority) {
		Task task = repo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
		task.setTaskpriority(priority);
		Task saved = repo.save(task);
		return mapToTaskListDTO(saved);
	}

	
	private TaskListDTO mapToTaskListDTO(Task t) {
		return new TaskListDTO(
				t.getTaskid(), 
				t.getTasktitle(), 
				t.getTaskdescription(),
				t.getTaskpriority(),
				t.getTaskcategory(),
				t.getDueDate(),
				t.isTaskcompleted()
				);
	}

	private TaskDetailsDTO mapToTaskDetailsDTO(Task t) {
		return TaskDetailsDTO.builder()
				.taskid(t.getTaskid())
				.tasktitle(t.getTasktitle())
				.taskdescription(t.getTaskdescription())
				.taskcompleted(t.isTaskcompleted())
				.taskcategory(t.getTaskcategory())
				.createdAt(t.getCreatedAt())
				.dueDate(t.getDueDate())
				.taskpriority(t.getTaskpriority())
				.userid(t.getUser().getId())
				.build();
	}
}
