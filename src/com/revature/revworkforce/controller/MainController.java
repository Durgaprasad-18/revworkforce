package com.revature.revworkforce.controller;
import java.util.Scanner;

public class MainController {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        Scanner sc = new Scanner(System.in);
        AuthController authController = new AuthController();
        RegistrationController registrationController = new RegistrationController();
        
        while (true) {
            System.out.println("========================================");
            System.out.println("        REV WORKFORCE HRM SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3 Forgot Password");
            System.out.println("4. Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter your choice: ");

            int choice = -1;

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(" Invalid input. Enter numbers only.");
                sc.nextLine(); // clear invalid input
                continue; // go back to menu
            }

            switch (choice) {
                case 1:
                    authController.login(sc);
                    break;
                case 2:
                    registrationController.register(sc);
                    break;
                    
                case 3:
                    authController.forgotPassword(sc);
                    break;
                case 4:
                    System.out.println("Thank you for using RevWorkForce!");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}


