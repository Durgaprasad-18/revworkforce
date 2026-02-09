package com.revature.revworkforce.controller;
import java.util.Scanner;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.service.RegistrationService;
public class RegistrationController {
	
	private RegistrationService registrationService = new RegistrationService();

    public void register(Scanner sc) {

        Employee emp = new Employee();

        System.out.print("Enter Employee ID: ");
        emp.setEmployeeId(sc.nextInt());

        System.out.print("Enter Name: ");
        emp.setName(sc.next());

        System.out.print("Enter Email: ");
        emp.setEmail(sc.next());

        System.out.print("Enter Password: ");
        emp.setPassword(sc.next());

        System.out.print("Enter Role (EMPLOYEE / MANAGER / ADMIN): ");
        emp.setRole(sc.next().toUpperCase());
        sc.nextLine(); 
        
        System.out.print("Enter Security Question: ");
        String question = sc.nextLine();
        while (question.trim().isEmpty()) {
            System.out.println("Security Question cannot be empty.");
            System.out.print("Enter Security Question: ");
            question = sc.nextLine();
        }
        emp.setSecurityQuestion(question);

        System.out.print("Enter Security Answer: ");
        String answer = sc.nextLine();

        while (answer.trim().isEmpty()) {
            System.out.println("Security Answer cannot be empty.");
            System.out.print("Enter Security Answer: ");
            answer = sc.nextLine();
        }
        emp.setSecurityAnswer(answer);

        boolean result = registrationService.register(emp);

        if (result) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed!");
        }
    }

}
