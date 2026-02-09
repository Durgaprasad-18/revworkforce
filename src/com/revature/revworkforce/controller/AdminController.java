package com.revature.revworkforce.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.service.AdminService;

public class AdminController {
	
	private final static AdminService adminService = new AdminService();

    public static void adminDashboard(Scanner sc, Employee admin) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        ADMIN DASHBOARD");
            System.out.println("Welcome, " + admin.getName());
            System.out.println("=================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Activate / Deactivate Employee");
            System.out.println("5. Assign Manager");
            System.out.println("\nORG MANAGEMENT");
            System.out.println("6 Manage Departments");
            System.out.println("7 Manage Designations");
            System.out.println("\nLEAVE MANAGEMENT");
            System.out.println("8 Configure Leave Types");
            System.out.println("9 Assign Leave Quota");
            System.out.println("10 Manage Holidays");
            System.out.println("11. View All Leave Applications");
            System.out.println("12. Cancel Leave (Override)");
            System.out.println("\nSYSTEM");
            System.out.println("13. View Audit Logs");
            System.out.println("14. Logout");

            System.out.print("Enter choice: ");

            try {

            	int choice;
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    sc.nextLine();
                    continue;
                }

                switch (choice) {

                    case 1:
                        addEmployee(sc);
                        break;

                    case 2:
                        viewAllEmployees();
                        break;
                        
                    case 3:
                        searchEmployee(sc);
                        break;
                    case 4:
                        changeEmployeeStatus(sc);
                        break;

                    case 5:
                        assignManager(sc);
                        break;
                        
                    case 6:
                        departmentMenu(sc,admin);
                        break;
                    case 7:
                        designationMenu(sc);
                        break;
                    case 8:
                        leaveTypeMenu(sc,admin);
                        break;
                        
                    case 9:
                        assignLeaveQuota(sc,admin);
                        break;
                    case 10:
                        holidayMenu(sc,admin);
                        break;

                    case 11:
                        viewAllLeaves();
                        break;

                    case 12:
                        cancelLeaveOverride(sc);
                        break;

                    case 13:
                        adminService.viewAuditLogs();
                        break;

                    case 14:
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error occurred. Try again.");
                sc.nextLine();
            }
        }
    }

    // =============================
    // ADD EMPLOYEE
    // =============================

    private static void addEmployee(Scanner sc) {

        sc.nextLine();

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Enter Role (EMPLOYEE / MANAGER / ADMIN): ");
        String role = sc.nextLine();

        System.out.print("Enter Manager ID (or 0 if none): ");
        int managerId = sc.nextInt();

        boolean result = adminService.addEmployee(
                id, name, email, password, role, managerId);

        if (result)
            System.out.println("Employee added successfully");
        else
            System.out.println("Failed to add employee");
    }

    // =============================
    // VIEW EMPLOYEES
    // =============================

    private static void viewAllEmployees() {

        List<Employee> list = adminService.getAllEmployees();

        if (list.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n--- Employee List ---");

        for (Employee e : list) {
            System.out.println(
                e.getEmployeeId() + " | " +
                e.getName() + " | " +
                e.getRole() + " | " +
                e.getStatus()
            );
        }
    }
    
    //-----search employee-----
    private static void searchEmployee(Scanner sc) {

        sc.nextLine();

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        List<Employee> list = adminService.searchEmployees(keyword);

        list.forEach(e ->
            System.out.println(e.getEmployeeId() + " | " +
                    e.getName() + " | " + e.getRole()));
    }

    // =============================
    // ACTIVATE / DEACTIVATE
    // =============================

    private static void changeEmployeeStatus(Scanner sc) {

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Status (ACTIVE / INACTIVE): ");
        String status = sc.nextLine();

        adminService.updateEmployeeStatus(empId, status);

        System.out.println("Status updated.");
    }

    // =============================
    // ASSIGN MANAGER
    // =============================

    private static void assignManager(Scanner sc) {

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        System.out.print("Enter Manager ID: ");
        int managerId = sc.nextInt();

        adminService.assignManager(empId, managerId);

        System.out.println("Manager assigned.");
    }
    
    //---add department ----
    private static void departmentMenu(Scanner sc,Employee admin) {

        System.out.println("1 Add Department");
        System.out.println("2 View Departments");

        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {
            System.out.print("Enter Department Name: ");
            adminService.addDepartment(admin.getEmployeeId(), sc.nextLine());
        }
        else if (ch == 2) {
            adminService.viewDepartments();
        }
    }
    
    //------designation menu--------
    private static void designationMenu(Scanner sc) {

        System.out.println("1 Add Designation");
        System.out.println("2 View Designations");

        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {
            System.out.print("Enter Designation Name: ");
            adminService.addDesignation(sc.nextLine());
        }
        else if (ch == 2) {
            adminService.viewDesignations();
        }
    }
    //----leave type-------
    private static void leaveTypeMenu(Scanner sc,Employee admin) {

        System.out.println("1 Add Leave Type");
        System.out.println("2 View Leave Types");

        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {
            System.out.print("Enter Leave Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Max Days: ");
            int limit = sc.nextInt();

            adminService.addLeaveType(admin.getEmployeeId(),
            	    name,
            	    limit);
        }
        else if (ch == 2) {
            adminService.viewLeaveTypes();
        }
    }
    
    //  leave quota------
    private static void assignLeaveQuota(Scanner sc,Employee admin) {

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        System.out.print("Enter Leave Type ID: ");
        int leaveTypeId = sc.nextInt();

        System.out.print("Enter Days: ");
        int days = sc.nextInt();

        adminService.assignLeaveQuota(  admin.getEmployeeId(),   // WHO performed action
                empId,                   // WHO received leave
                leaveTypeId,
                days);
    }
    
    // holiday menu
    private static void holidayMenu(Scanner sc,Employee admin) {

        System.out.println("1 Add Holiday");
        System.out.println("2 View Holidays");

        int ch = sc.nextInt();
        sc.nextLine();

        if (ch == 1) {

            System.out.print("Enter Holiday Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            adminService.addHoliday(admin.getEmployeeId(),
                    name,
                    date);
        }
        else if (ch == 2) {
            adminService.viewHolidays();
        }
    }

    // =============================
    // VIEW ALL LEAVES
    // =============================

    private static void viewAllLeaves() {

        List<LeaveApplication> leaves =
                adminService.getAllLeaves();

        for (LeaveApplication l : leaves) {

            System.out.println(
                l.getLeaveId() +
                " | Emp: " + l.getEmployeeId() +
                " | " + l.getStartDate() +
                " to " + l.getEndDate() +
                " | " + l.getStatus()
            );
        }
    }

    // =============================
    // CANCEL LEAVE OVERRIDE
    // =============================

    private static void cancelLeaveOverride(Scanner sc) {

        System.out.print("Enter Leave ID: ");
        int leaveId = sc.nextInt();

        adminService.cancelLeaveOverride(leaveId);

        System.out.println("Leave cancelled by admin.");
    }

}
