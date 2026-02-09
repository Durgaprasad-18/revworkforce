package com.revature.revworkforce.service;

import java.util.List;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.AnnouncementDAO;
import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.CompanyHolidayDAO;
import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.model.Announcement;
import com.revature.revworkforce.model.CompanyHoliday;
import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.util.LoggerUtil;

public class EmployeeService {
	
	private final Logger logger = LoggerUtil.getLogger();
	private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();

    public void viewProfile(Employee emp) {

        logger.info("Profile viewed for employeeId " + emp.getEmployeeId());
        auditLogDAO.logAction(emp.getEmployeeId(), "Viewed profile");
    }

	public boolean updateProfile(int employeeId, String phone, String address, String emergencyContact) {
		// TODO Auto-generated method stub
		if (phone == null || address == null || emergencyContact == null) {
            logger.warning("Profile update failed due to invalid input for employeeId " + employeeId);
            return false;
        }

        boolean updated = employeeDAO.updateProfile(
                employeeId, phone, address, emergencyContact);

        if (updated) {
            logger.info("Profile updated for employeeId " + employeeId);
            auditLogDAO.logAction(employeeId, "Updated profile information");
        } else {
            logger.warning("Profile update failed for employeeId " + employeeId);
        }

        return updated;
		
		
	}


	public void viewManagerDetails(int employeeId) {
		// TODO Auto-generated method stub
	    Employee manager = employeeDAO.getManagerDetails(employeeId);
	    if (manager != null) {
	        System.out.println("\n--- Reporting Manager Details ---");
	        System.out.println("Manager ID   : " + manager.getEmployeeId());
	        System.out.println("Name         : " + manager.getName());
	        System.out.println("Email        : " + manager.getEmail());
	        System.out.println("Phone        : " + manager.getPhone());
	    } else {
	        System.out.println("No manager assigned.");
	    }

	    logger.info("Viewed manager details for employeeId " + employeeId);
	    auditLogDAO.logAction(employeeId, "Viewed manager details");

		
	}

	public void viewCompanyHolidays( int employeeId) {
		// TODO Auto-generated method stub
		
		List<CompanyHoliday> holidays = CompanyHolidayDAO.getAllHolidays();

	    if (holidays.isEmpty()) {
	        System.out.println("No company holidays configured.");
	        return;
	    }

	    System.out.println("\n--- Company Holiday Calendar ---");
	    for (CompanyHoliday h : holidays) {
	        System.out.println(
	            h.getHolidayDate() + " | " +
	            h.getHolidayName() 
	        );
	    }

	    logger.info("Viewed company holiday calendar");
	    auditLogDAO.logAction(employeeId, "Viewed company holidays");
		
	}

	public void viewAnnouncements() {
		// TODO Auto-generated method stub
		
		List<Announcement> announcements =
	            AnnouncementDAO.getAllAnnouncements();

	    if (announcements.isEmpty()) {
	        System.out.println("No announcements available.");
	        return;
	    }

	    System.out.println("\n--- Company Announcements ---");
	    for (Announcement a : announcements) {
	        System.out.println(
	            a.getCreatedAt() + " | " + a.getTitle()
	        );
	        System.out.println(a.getMessage());
	        System.out.println("----------------------------------");
	    }

	    logger.info("Viewed company announcements");
	    auditLogDAO.logAction(0, "Viewed announcements");	    
	}
	
	public List<Employee> searchEmployees(String keyword) {
	    return employeeDAO.searchEmployees(keyword);
	}

}
