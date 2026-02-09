package com.revature.revworkforce.controller;

import java.util.Scanner;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.service.AuthService;

public class AuthController {
	
	private AuthService authService = new AuthService();

	public void login(Scanner sc) {

	    System.out.print("Enter Employee ID: ");
	    int employeeId = sc.nextInt();

	    sc.nextLine();

	    System.out.print("Enter Password: ");
	    String password = sc.nextLine();

	    Employee emp = authService.login(employeeId, password);

	    if (emp == null) {
	        System.out.println("Invalid credentials");
	        return;
	    }

	    System.out.println("Login successful!");

	    //  ROLE BASED ROUTING
	    switch (emp.getRole().toUpperCase()) {

	        case "EMPLOYEE":
	            EmployeeController.employeeDashboard(sc, emp);
	            break;

	        case "MANAGER":
	            ManagerController.managerDashboard(sc, emp);
	            break;

	        case "ADMIN":
	            AdminController.adminDashboard(sc, emp);
	            break;

	        default:
	            System.out.println("Invalid role assigned.");
	    }
	    
	    
	   

	}
	 public  void forgotPassword(Scanner sc) {

	        System.out.print("Enter Employee ID: ");
	        int empId = sc.nextInt();
	        sc.nextLine();

	        System.out.print("Enter Security Answer: ");
	        String answer = sc.nextLine();

	        System.out.print("Enter New Password: ");
	        String newPass = sc.nextLine();

	        boolean result =
	                authService.forgotPassword(empId, answer, newPass);

	        if (result)
	            System.out.println("Password reset successful.");
	        else
	            System.out.println("Password reset failed.");
	    }

	
}
