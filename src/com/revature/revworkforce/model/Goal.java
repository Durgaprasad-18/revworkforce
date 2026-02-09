package com.revature.revworkforce.model;

import java.sql.Date;

public class Goal {
    private int goalId;
    private int employeeId;
    private String description;
    private Date deadline;
    private String priority;
    private int progress;
	public Goal() {
		super();
	}
	public Goal(int goalId, int employeeId, String description, Date deadline, String priority, int progress) {
		super();
		this.goalId = goalId;
		this.employeeId = employeeId;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.progress = progress;
	}
	public int getGoalId() {
		return goalId;
	}
	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
    
    

}
