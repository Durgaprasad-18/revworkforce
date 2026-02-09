package com.revature.revworkforce.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.model.PerformanceReview;
import com.revature.revworkforce.service.LeaveService;
import com.revature.revworkforce.service.ManagerService;
import com.revature.revworkforce.service.PerformanceService;

public class ManagerController {
	
	private final static ManagerService managerService = new ManagerService();
    private final static LeaveService leaveService = new LeaveService();
    private final static PerformanceService performanceService = new PerformanceService();

    public static void managerDashboard(Scanner sc, Employee manager) {

        while (true) {

            System.out.println("\n=================================");
            System.out.println("        MANAGER DASHBOARD");
            System.out.println("Welcome, " + manager.getName());
            System.out.println("=================================");
            System.out.println("1. View Team Members");
            System.out.println("2. View Team Leave Requests");
            System.out.println("3. Approve / Reject Leave");
            System.out.println("4. View Team Performance Reviews");
            System.out.println("5. Give Performance Feedback");
            System.out.println("6. Logout");

            System.out.print("Enter choice: ");

            try {

                int choice = sc.nextInt();

                switch (choice) {

                case 1:
                    viewTeamMembers(manager);
                    break;

                case 2:
                    viewTeamLeaves(manager);
                    break;

                case 3:
                    approveRejectLeave(sc, manager);
                    break;

                case 4:
                    viewTeamPerformance(manager);
                    break;

                case 5:
                    givePerformanceFeedback(sc, manager);
                    break;

                case 6:
                    return;

                default:
                    System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println(" Error occurred. Try again.");
                sc.nextLine();
            }
        }
    }

    // =============================
    // VIEW TEAM MEMBERS
    // =============================

    private static void viewTeamMembers(Employee manager) {

        List<Employee> team =
                managerService.getTeamMembers(manager.getEmployeeId());

        if (team.isEmpty()) {
            System.out.println("No team members found.");
            return;
        }

        System.out.println("\n--- Team Members ---");

        for (Employee e : team) {
            System.out.println(
                e.getEmployeeId() + " | " +
                e.getName() + " | " +
                e.getEmail()
            );
        }
    }

    // =============================
    // VIEW TEAM LEAVES
    // =============================

    private static void viewTeamLeaves(Employee manager) {

        List<LeaveApplication> leaves =
                managerService.getTeamLeaves(manager.getEmployeeId());

        if (leaves.isEmpty()) {
            System.out.println("No leave requests.");
            return;
        }

        System.out.println("\n--- Team Leave Requests ---");

        for (LeaveApplication l : leaves) {
            System.out.println(
                "LeaveID: " + l.getLeaveId() +
                " | EmpID: " + l.getEmployeeId() +
                " | From: " + l.getStartDate() +
                " | To: " + l.getEndDate() +
                " | Reason: " + l.getReason() +
                " | Status: " + l.getStatus()
            );
        }
    }

    // =============================
    // APPROVE / REJECT LEAVE
    // =============================

    private static void approveRejectLeave(Scanner sc, Employee manager) {

        try {

            System.out.print("Enter Leave ID: ");
            int leaveId = sc.nextInt();

            LeaveApplication leave =
                    leaveService.getLeaveDetails(leaveId);

            if (leave == null) {
                System.out.println("Leave not found.");
                return;
            }

            //  SHOW FULL DETAILS BEFORE DECISION
            System.out.println("\n--- Leave Details ---");
            System.out.println("Employee ID : " + leave.getEmployeeId());
            System.out.println("Start Date  : " + leave.getStartDate());
            System.out.println("End Date    : " + leave.getEndDate());
            System.out.println("Reason      : " + leave.getReason());
            System.out.println("Status      : " + leave.getStatus());

            sc.nextLine();

            System.out.print("Enter Decision (APPROVED / REJECTED): ");
            String status = sc.nextLine();

            leaveService.updateLeaveStatus(
                    leaveId,
                    status,
                    manager.getEmployeeId());

            System.out.println("Leave status updated.");

        } catch (Exception e) {
            System.out.println("Error updating leave.");
            sc.nextLine();
        }
    }

    // =============================
    // VIEW TEAM PERFORMANCE
    // =============================

    private static void viewTeamPerformance(Employee manager) {

        List<PerformanceReview> reviews =
                managerService.getTeamPerformance(manager.getEmployeeId());

        if (reviews.isEmpty()) {
            System.out.println("No performance reviews.");
            return;
        }

        System.out.println("\n--- Team Performance Reviews ---");

        for (PerformanceReview r : reviews) {

            System.out.println(
                "ReviewID: " + r.getReviewId() +
                " | EmpID: " + r.getEmployeeId() +
                " | Self Rating: " + r.getSelfRating() +
                " | Feedback: " + r.getFeedback()
            );
        }
    }

    // =============================
    // GIVE FEEDBACK
    // =============================

    private static void givePerformanceFeedback(Scanner sc, Employee manager) {

        try {

            System.out.print("Enter Review ID: ");
            int reviewId = sc.nextInt();

            PerformanceReview review =
                    performanceService.getReviewDetails(reviewId);

            if (review == null) {
                System.out.println("Review not found.");
                return;
            }

            //  SHOW REVIEW DETAILS FIRST
            System.out.println("\n--- Review Details ---");
            System.out.println("Employee ID : " + review.getEmployeeId());
            System.out.println("Self Rating : " + review.getSelfRating());

            sc.nextLine();

            System.out.print("Enter Manager Rating (1-5): ");
            int rating = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Feedback: ");
            String feedback = sc.nextLine();

            performanceService.giveManagerFeedback(
                    reviewId,
                    rating,
                    feedback,
                    manager.getEmployeeId());

            System.out.println("Feedback submitted.");

        } catch (Exception e) {
            System.out.println("Error submitting feedback.");
            sc.nextLine();
        }
    }

}
