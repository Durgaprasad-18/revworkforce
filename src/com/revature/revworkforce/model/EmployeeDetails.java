package com.revature.revworkforce.model;
import java.sql.Date;

public class EmployeeDetails {
    private int employeeId;
    private int departmentId;
    private int designationId;
    private Date joiningDate;
    private double salary;
	public EmployeeDetails() {
		super();
	}
	public EmployeeDetails(int employeeId, int departmentId, int designationId, Date joiningDate, double salary) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.joiningDate = joiningDate;
		this.salary = salary;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
    
    
	

}
