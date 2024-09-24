package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.User;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>{
	List<Task> findByUser(User user);
	List<Task> findByUserAndTaskTitleContaining(User user, String taskTitle);
}
