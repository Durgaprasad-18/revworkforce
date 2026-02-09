package com.revature.revworkforce.service;

import java.util.List;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.dao.LeaveDAO;
import com.revature.revworkforce.dao.PerformanceDAO;
import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.model.PerformanceReview;
import com.revature.revworkforce.util.LoggerUtil;

public class ManagerService {
	
	 private final Logger logger = LoggerUtil.getLogger();

	    private final EmployeeDAO employeeDAO = new EmployeeDAO();
	    private final LeaveDAO leaveDAO = new LeaveDAO();
	    private final PerformanceDAO performanceDAO = new PerformanceDAO();

	    // =========================
	    // TEAM MEMBERS
	    // =========================
	    public List<Employee> getTeamMembers(int managerId) {

	        logger.info("Fetching team members for manager " + managerId);

	        return employeeDAO.getEmployeesByManager(managerId);
	    }

	    // =========================
	    // TEAM LEAVES
	    // =========================
	    public List<LeaveApplication> getTeamLeaves(int managerId) {

	        logger.info("Fetching team leaves for manager " + managerId);

	        return leaveDAO.getLeavesByManager(managerId);
	    }

	    // =========================
	    // TEAM PERFORMANCE
	    // =========================
	    public List<PerformanceReview> getTeamPerformance(int managerId) {

	        logger.info("Fetching team performance for manager " + managerId);

	        return performanceDAO.getReviewsByManager(managerId);
	    }

}
