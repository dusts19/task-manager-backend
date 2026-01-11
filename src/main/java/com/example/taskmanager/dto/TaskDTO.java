package com.example.taskmanager.dto;

import com.example.taskmanager.model.Task;

import com.example.taskmanager.model.User;
import com.example.taskmanager.model.Task.Priority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
	private long taskid;
	private String tasktitle;
	private String taskdescription;
	private boolean taskcompleted;
	private String taskcategory;
	private LocalDateTime createdAt;
	private LocalDate dueDate;
	private Task.Priority taskpriority;
	private long userid;
	
////	public TaskDTO(long taskid, String tasktitle, String taskdescription, boolean taskcompleted, Task.Priority taskpriority) {
////		this.taskid = taskid;
////		this.tasktitle = tasktitle;
////		this.taskdescription = taskdescription;
////		this.taskcompleted = taskcompleted;
////		this.taskpriority = taskpriority;
////	}
//	
//	public TaskDTO(long taskid, String tasktitle, String taskdescription, boolean taskcompleted, String taskcategory, LocalDateTime createdAt, LocalDate dueDate, Task.Priority taskpriority, long userid) {
//		this.taskid = taskid;
//		this.tasktitle = tasktitle;
//		this.taskdescription = taskdescription;
//		this.taskcompleted = taskcompleted;
//		this.taskcategory = taskcategory;
//		this.createdAt = createdAt;
//		this.dueDate = dueDate;
//		this.taskpriority = taskpriority;
//		this.userid = userid;
//	}
//
//	
//	public long getTaskid() {
//		return taskid;
//	}
//	public void setTaskid(long taskid) {
//		this.taskid = taskid;
//	}
//	public String getTasktitle() {
//		return tasktitle;
//	}
//	public void setTasktitle(String tasktitle) {
//		this.tasktitle = tasktitle;
//	}
//	public String getTaskdescription() {
//		return taskdescription;
//	}
//	public void setTaskdescription(String taskdescription) {
//		this.taskdescription = taskdescription;
//	}
//	public boolean isTaskcompleted() {
//		return taskcompleted;
//	}
//	public void setTaskcompleted(boolean taskcompleted) {
//		this.taskcompleted = taskcompleted;
//	}
//	public String getTaskcategory() {
//		return taskcategory;
//	}
//	public void setTaskcategory(String taskcategory) {
//		this.taskcategory = taskcategory;
//	}
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//	public LocalDate getDueDate() {
//		return dueDate;
//	}
//	public void setDueDate(LocalDate dueDate) {
//		this.dueDate = dueDate;
//	}
//	public Task.Priority getTaskpriority() {
//		return taskpriority;
//	}
//	public void setTaskpriority(Task.Priority taskpriority) {
//		this.taskpriority = taskpriority;
//	}
//	public long getUserid() {
//		return userid;
//	}
//	public void setUserid(long userid) {
//		this.userid = userid;
//	}
//	
	
	
}
