package com.revature.revworkforce.model;
import java.sql.Timestamp;

public class Notification {
	
    private int notificationId;
    private int employeeId;
    private String message;
    private boolean isRead;
    private Timestamp createdAt;
	public Notification() {
		super();
	}
	public Notification(int notificationId, int employeeId, String message, boolean isRead, Timestamp createdAt) {
		super();
		this.notificationId = notificationId;
		this.employeeId = employeeId;
		this.message = message;
		this.isRead = isRead;
		this.createdAt = createdAt;
	}
	public int getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    
    

}
