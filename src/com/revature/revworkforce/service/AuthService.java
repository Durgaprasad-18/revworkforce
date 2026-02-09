package com.revature.revworkforce.service;

import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.util.LoggerUtil;


public class AuthService {
	
    private final Logger logger = LoggerUtil.getLogger();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();
    
    public Employee login(int employeeId, String password) {

	    Employee emp = employeeDAO.login(employeeId, password);
	
	    if (emp != null) {
	        logger.info("Login successful for employeeId " + employeeId);
	        auditLogDAO.logAction(employeeId, "Logged in");
	    } else {
	        logger.warning("Login failed for employeeId " + employeeId);
	    }
	
	    return emp;
    }
    
    public boolean forgotPassword(
            int employeeId,
            String securityAnswer,
            String newPassword) {

        boolean verified =
            employeeDAO.verifySecurityAnswer(employeeId, securityAnswer);

        if (!verified) {
            System.out.println("Security verification failed.");
            return false;
        }

        boolean updated =
            employeeDAO.updatePassword(employeeId, newPassword);

        if (updated) {
            auditLogDAO.logAction(
                    employeeId,
                    "Password reset via forgot password");
        }

        return updated;
    }

}
