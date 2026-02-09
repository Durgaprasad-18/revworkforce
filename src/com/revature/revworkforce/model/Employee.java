package com.revature.revworkforce.model;

import java.sql.Timestamp;

public class Employee{

    private int employeeId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String dob;
    private String role;        // EMPLOYEE / MANAGER / ADMIN
    private Integer managerId;
    private String status;
    private String securityQuestion;
    private String securityAnswer;
    private Timestamp createdAt;
	public Employee() {
		super();
	}
	public Employee(int employeeId, String name, String email, String password, String phone, String address,
			String dob, String role, Integer managerId, String status, String securityQuestion,String securityAnswer,  Timestamp createdAt) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.role = role;
		this.managerId = managerId;
		this.status = status;
		this.securityQuestion=securityQuestion;
		this.securityAnswer=securityAnswer;
		this.createdAt = createdAt;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
}