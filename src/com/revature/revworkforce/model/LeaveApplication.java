package com.revature.revworkforce.model;

import java.sql.Timestamp;

public class LeaveApplication {
	private int leaveId;
    private int employeeId;
    private int leaveTypeId;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;
    private String managerComment;
    private Timestamp appliedAt;
	public LeaveApplication() {
		super();
	}
	public LeaveApplication(int leaveId, int employeeId, int leaveTypeId, String startDate, String endDate,
			String reason, String status, String managerComment, Timestamp appliedAt) {
		super();
		this.leaveId = leaveId;
		this.employeeId = employeeId;
		this.leaveTypeId = leaveTypeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = status;
		this.managerComment = managerComment;
		this.appliedAt = appliedAt;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getManagerComment() {
		return managerComment;
	}
	public void setManagerComment(String managerComment) {
		this.managerComment = managerComment;
	}
	public Timestamp getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(Timestamp appliedAt) {
		this.appliedAt = appliedAt;
	}
	

}
