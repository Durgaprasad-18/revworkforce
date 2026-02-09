package com.revature.revworkforce.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.service.EmployeeService;
import com.revature.revworkforce.service.GoalService;
import com.revature.revworkforce.service.LeaveService;
import com.revature.revworkforce.service.NotificationService;
import com.revature.revworkforce.service.PerformanceService;

public class EmployeeController {

    private static final EmployeeService employeeService = new EmployeeService();
    private static final LeaveService leaveService = new LeaveService();
    private static final PerformanceService performanceService = new PerformanceService();
    private static final GoalService goalService = new GoalService();
    private static final NotificationService notificationService = new NotificationService();

    // ================= DASHBOARD =================
    public static void employeeDashboard(Scanner sc, Employee emp) {

        while (true) {
            System.out.println("\n=================================");
            System.out.println("EMPLOYEE DASHBOARD");
            System.out.println("Welcome, " + emp.getName());
            System.out.println("=================================");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. View Manager Details");
            System.out.println("4. Leave Management");
            System.out.println("5. Performance Management");
            System.out.println("6. View Notifications");
            System.out.println("7. View Announcements");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewProfile(emp);
                    break;

                case 2:
                    editProfile(sc, emp);
                    break;

                case 3:
                    employeeService.viewManagerDetails(emp.getEmployeeId());
                    break;

                case 4:
                    leaveMenu(sc, emp);
                    break;

                case 5:
                    performanceMenu(sc, emp);
                    break;

                case 6:
                    notificationService.viewNotifications(emp.getEmployeeId());
                    break;

                case 7:
                    employeeService.viewAnnouncements();
                    break;

                case 8:
                    System.out.println("Logged out successfully!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ================= PROFILE =================
    private static void viewProfile(Employee emp) {
        System.out.println("\n--- Profile Details ---");
        System.out.println("Employee ID: " + emp.getEmployeeId());
        System.out.println("Name       : " + emp.getName());
        System.out.println("Email      : " + emp.getEmail());
        System.out.println("Role       : " + emp.getRole());
    }

    private static void editProfile(Scanner sc, Employee emp) {

        // ===== PHONE VALIDATION =====
        String phone = null;
        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter new 10 digit phone number: ");
            phone = sc.next();

            if (phone.matches("\\d{10}")) {
                break; // valid
            } else {
                attempts++;
                System.out.println("Invalid phone number. Must be exactly 10 digits.");

                if (attempts == 3) {
                    System.out.println("Max attempts reached. Returning to dashboard.");
                    return;
                }
            }
        }

        // ===== ADDRESS =====
        sc.nextLine();
        System.out.print("Enter new address: ");
        String address = sc.nextLine();

        // ===== EMERGENCY CONTACT =====
        System.out.print("Enter emergency contact: ");
        String emergency = sc.next();

        employeeService.updateProfile(
                emp.getEmployeeId(),
                phone,
                address,
                emergency
        );

        System.out.println("Profile updated successfully.");
    }


    // ================= LEAVE MENU =================
    private static void leaveMenu(Scanner sc, Employee emp) {

        while (true) {

            System.out.println("\n--- Leave Management ---");
            System.out.println("1. View Leave Balance");
            System.out.println("2. Apply Leave");
            System.out.println("3. View Applied Leaves");
            System.out.println("4. Cancel Leave");
            System.out.println("5. View Holidays");
            System.out.println("6. Back");
            System.out.print("Enter choice: "); 

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    leaveService.viewLeaveBalance(emp.getEmployeeId());
                    break;

                case 2:
                    applyLeave(sc, emp);
                    break;

                case 3:
                    List<LeaveApplication> leaves =
                            leaveService.viewLeaves(emp.getEmployeeId());

                    leaves.forEach(l ->
                            System.out.println(l.getLeaveId() + " | " + l.getStatus())
                    );
                    break;

                case 4:
                    System.out.print("Enter Leave ID: ");
                    int leaveId = sc.nextInt();
                    leaveService.cancelLeave(emp.getEmployeeId(), leaveId);
                    break;

                case 5:
                    employeeService.viewCompanyHolidays(emp.getEmployeeId());
                    break;

                case 6:
                    return;
            }
        }
    }

    // ================= APPLY LEAVE =================
    private static void applyLeave(Scanner sc, Employee emp) {

        LeaveApplication leave = new LeaveApplication();
        leave.setEmployeeId(emp.getEmployeeId());

        System.out.print("Enter Leave Type ID: ");
        leave.setLeaveTypeId(sc.nextInt());
        sc.nextLine();

        java.sql.Date startDate = null;
        int attempts = 0;

        while (startDate == null && attempts < 3) {
            try {
                System.out.print("Enter Start Date (YYYY-MM-DD): ");
                startDate = java.sql.Date.valueOf(sc.nextLine());
            } catch (Exception e) {
                attempts++;
                System.out.println("Invalid date format");

                if (attempts == 3) {
                    System.out.println("Max retries reached.");
                    return;
                }
            }
        }

        java.sql.Date endDate = null;
        attempts = 0;

        while (endDate == null && attempts < 3) {
            try {
                System.out.print("Enter End Date (YYYY-MM-DD): ");
                endDate = java.sql.Date.valueOf(sc.nextLine());
            } catch (Exception e) {
                attempts++;
                System.out.println("Invalid date format");

                if (attempts == 3) {
                    System.out.println("Max retries reached.");
                    return;
                }
            }
        }

        if (endDate.before(startDate)) {
            System.out.println("End date cannot be before start date.");
            return;
        }

        System.out.print("Enter Reason: ");
        String reason = sc.nextLine();

        leave.setStartDate(startDate.toString());
        leave.setEndDate(endDate.toString());
        leave.setReason(reason);

        int days = (int)((endDate.getTime() - startDate.getTime())
                / (1000 * 60 * 60 * 24)) + 1;

        boolean result = leaveService.applyLeave(leave, days);

        if (result)
            System.out.println("Leave applied successfully.");
        else
            System.out.println("Leave application failed.");
    }

    // ================= PERFORMANCE MENU =================
    private static void performanceMenu(Scanner sc, Employee emp) {

        while (true) {

            System.out.println("\n--- Performance Management ---");
            System.out.println("1. Submit Review");
            System.out.println("2. Create Goal");
            System.out.println("3. Update Goal Progress");
            System.out.println("4. View Feedback");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    performanceService.submitReview(emp.getEmployeeId());
                    System.out.println("Review submitted.");
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Enter Goal Description: ");
                    String desc = sc.nextLine();

                    System.out.print("Enter Deadline (YYYY-MM-DD): ");
                    java.sql.Date deadline =
                            java.sql.Date.valueOf(sc.nextLine());

                    System.out.print("Enter Priority: ");
                    String priority = sc.nextLine();

                    boolean created = goalService.createGoal(
                            emp.getEmployeeId(), desc, deadline, priority);

                    if (created)
                        System.out.println("Goal created.");
                    else
                        System.out.println("Goal creation failed.");
                    break;

                case 3:
                    System.out.print("Enter Goal ID: ");
                    int goalId = sc.nextInt();

                    System.out.print("Enter Progress %: ");
                    int progress = sc.nextInt();

                    goalService.updateGoalProgress(
                            emp.getEmployeeId(), goalId, progress);
                    break;

                case 4:
                    performanceService.viewManagerFeedback(emp.getEmployeeId());
                    break;

                case 5:
                    return;
            }
        }
    }
}
