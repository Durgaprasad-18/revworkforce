package com.revature.revworkforce.model;

public class LeaveBalance {
	
	 private int balanceId;
	 private int employeeId;
	 private int leaveTypeId;
	 private int remainingDays;
	public LeaveBalance() {
		super();
	}
	public LeaveBalance(int balanceId, int employeeId, int leaveTypeId, int remainingDays) {
		super();
		this.balanceId = balanceId;
		this.employeeId = employeeId;
		this.leaveTypeId = leaveTypeId;
		this.remainingDays = remainingDays;
	}
	public int getBalanceId() {
		return balanceId;
	}
	public void setBalanceId(int balanceId) {
		this.balanceId = balanceId;
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
	public int getRemainingDays() {
		return remainingDays;
	}
	public void setRemainingDays(int remainingDays) {
		this.remainingDays = remainingDays;
	}
	 
	 

}
