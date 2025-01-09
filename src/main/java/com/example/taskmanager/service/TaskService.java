package com.example.taskmanager.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo repo;
	
	@Autowired
	UserService userService;
	
//	@Transactional
//	public List<Task> getTasksByUser(User user) {
//		return repo.findByUser(user);
//	}
	@Transactional
	public List<TaskDTO> getTasksByUser(User user) {
		List<Task> tasks = repo.findByUser(user);
		List<TaskDTO> taskDTOs = tasks.stream()
				.map(task -> new TaskDTO(task.getTaskid(), task.getTasktitle(), task.getTaskdescription(), task.isTaskcompleted(), task.getTaskpriority(), task.getUser().getId()))
				.collect(Collectors.toList());
		return taskDTOs;
	}
	
	public List<Task> getTasksByUserAndTitle(User user, String tasktitle){
		return repo.findByUserAndTasktitleContaining(user, tasktitle);
	}
	
	public List<Task> getTasks(){
		return repo.findAll();
	}
	
	public Task getTaskById(long taskId) {
		return repo.findById(taskId).orElse(new Task());
	}

	public Task addTask(Task task) {
		return repo.save(task);
	}
//	public Task addTask(Task task, String username) {
//		User user = userService.findByUsername(username);
//		task.setUser(user);
//		return repo.save(task);
//	}
	
	public Task updateTask(Task task, String username) {
		User user = userService.findByUsername(username);
		task.setUser(user);
		return repo.save(task);
	}
	
	public void deleteTask(long taskId) {
		repo.deleteById(taskId);
	}
	
	public Task updateTaskPriority(long id, Task.Priority priority) {
		Task task = getTaskById(id);
		if (task != null) {
			task.setTaskpriority(priority);
			return repo.save(task);
		}
		return null;
	}

}
