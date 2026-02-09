package com.revature.revworkforce.service;

import java.util.List;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.dao.LeaveBalanceDAO;
import com.revature.revworkforce.dao.LeaveDAO;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.model.LeaveBalance;
import com.revature.revworkforce.util.LoggerUtil;

public class LeaveService {
    private final Logger logger = LoggerUtil.getLogger();
    private final LeaveDAO leaveDAO = new LeaveDAO();
    private final LeaveBalanceDAO balanceDAO = new LeaveBalanceDAO();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();
    //private final NotificationService notificationService = new NotificationService();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    
    //------apply leave------
    
    public boolean applyLeave(LeaveApplication leave, int days) {

        int balance = balanceDAO.getRemainingDays(
                leave.getEmployeeId(),
                leave.getLeaveTypeId());

        if (days > balance) {
            logger.warning("Insufficient leave balance for emp " + leave.getEmployeeId());
            return false;
        }

        boolean result = leaveDAO.applyLeave(leave);

        if (result) {

            balanceDAO.updateLeaveBalance(
                    leave.getEmployeeId(),
                    leave.getLeaveTypeId(),
                    balance - days);

            //  Notify Manager
            int managerId = employeeDAO.getManagerId(leave.getEmployeeId());

            NotificationService.sendNotification(
                    managerId,
                    "Employee " + leave.getEmployeeId() + " applied for leave");

            logger.info("Leave applied by employeeId " + leave.getEmployeeId());
            auditLogDAO.logAction(
                    leave.getEmployeeId(),
                    "Applied leave (" + days + " days)");
        }

        return result;
    }
    
    //----view leave balance---------

    public void viewLeaveBalance(int employeeId) {

        List<LeaveBalance> balances =
                balanceDAO.getLeaveBalances(employeeId);

        if (balances.isEmpty()) {
            System.out.println("No leave balance found.");
            return;
        }

        System.out.println("\n--- Leave Balance ---");

        for (LeaveBalance lb : balances) {

            System.out.println(
                "Leave Type ID : " + lb.getLeaveTypeId() +
                " | Remaining Days : " + lb.getRemainingDays()
            );
        }

        logger.info("Viewed leave balance for employeeId " + employeeId);
        auditLogDAO.logAction(employeeId, "Viewed leave balance");
    }

	
	
	//-----view leaves-----

	public List<LeaveApplication> viewLeaves(int employeeId) {
		// TODO Auto-generated method stub
		logger.info("Viewed applied leaves for employeeId " + employeeId);
        return leaveDAO.getLeavesByEmployee(employeeId);
	}
	
	//---------cancel leave-------
	public boolean cancelLeave(int employeeId, int leaveId) {
		// TODO Auto-generated method stub
		 boolean result = leaveDAO.cancelLeave(employeeId, leaveId);

	        if (result) {
	            logger.info("Cancelled leaveId " + leaveId);
	            auditLogDAO.logAction(employeeId, "Cancelled leave ID " + leaveId);
	        }
	        return result;
	}
	
	//-----manager methods-------
	//-----leave details ----------
	
	public LeaveApplication getLeaveDetails(int leaveId) {

	    logger.info("Fetching leave details for leaveId " + leaveId);

	    return leaveDAO.getLeaveById(leaveId);
	}
	
	//--------manager approves or rejects  the leaves.
	
	 public void updateLeaveStatus(int leaveId,
             String status,
             int managerId) {

			leaveDAO.updateLeaveStatus(leaveId, status);
			
			LeaveApplication leave = leaveDAO.getLeaveById(leaveId);
			
			//  Notify Employee
			NotificationService.sendNotification(
			leave.getEmployeeId(),
			"Your leave request is " + status);
			
			logger.info("Manager " + managerId +
			" updated leave " + leaveId +
			" to " + status);
			}
}

