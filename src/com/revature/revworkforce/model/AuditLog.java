package com.revature.revworkforce.model;

import java.sql.Timestamp;

public class AuditLog {
    private int logId;
    private int employeeId;
    private String action;
    private Timestamp logTime;
	public AuditLog() {
		super();
	}
	public AuditLog(int logId, int employeeId, String action, Timestamp logTime) {
		super();
		this.logId = logId;
		this.employeeId = employeeId;
		this.action = action;
		this.logTime = logTime;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Timestamp getLogTime() {
		return logTime;
	}
	public void setLogTime(Timestamp logTime) {
		this.logTime = logTime;
	}
    
    

}
