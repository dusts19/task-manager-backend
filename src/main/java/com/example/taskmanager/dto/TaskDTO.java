package com.example.taskmanager.dto;

import com.example.taskmanager.model.Task;

public class TaskDTO {
	private String tasktitle;
	private String taskdescription;
	private boolean taskcompleted;
	private Task.Priority taskpriority;
	private long userid;
	
	
	public String getTasktitle() {
		return tasktitle;
	}
	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}
	public String getTaskdescription() {
		return taskdescription;
	}
	public void setTaskdescription(String taskdescription) {
		this.taskdescription = taskdescription;
	}
	public boolean isTaskcompleted() {
		return taskcompleted;
	}
	public void setTaskcompleted(boolean taskcompleted) {
		this.taskcompleted = taskcompleted;
	}
	public Task.Priority getTaskpriority() {
		return taskpriority;
	}
	public void setTaskpriority(Task.Priority taskpriority) {
		this.taskpriority = taskpriority;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	
	
}
