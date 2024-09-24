package com.example.taskmanager.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo repo;
	
	@Autowired
	UserService userService;
	
	public List<Task> getTasksByUser(User user) {
		return repo.findByUser(user);
	}
	
	public List<Task> getTasksByUserAndTitle(User user, String taskTitle){
		return repo.findByUserAndTaskTitleContaining(user, taskTitle);
	}
	
	public List<Task> getTasks(){
		return repo.findAll();
	}
	
	public Task getTaskById(long taskId) {
		return repo.findById(taskId).orElse(new Task());
	}
	
	public Task addTask(Task task, String username) {
		User user = userService.findByUsername(username);
		task.setUser(user);
		return repo.save(task);
	}

	public Task updateTask(Task task) {
		return repo.save(task);
	}
	
	public void deleteTask(long taskId) {
		repo.deleteById(taskId);
	}
	
	public Task updateTaskPriority(long id, Task.Priority priority) {
		Task task = getTaskById(id);
		if (task != null) {
			task.setTaskPriority(priority);
			return repo.save(task);
		}
		return null;
	}

}
