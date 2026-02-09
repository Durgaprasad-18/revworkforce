package com.revature.revworkforce.service;

import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.util.LoggerUtil;

public class RegistrationService {
	
    private final Logger logger = LoggerUtil.getLogger();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();
    
    public boolean register (Employee emp) {
    
	    boolean result = employeeDAO.register(emp);
	
	    if (result) {
	        logger.info("User registered: " + emp.getEmployeeId());
	        auditLogDAO.logAction(emp.getEmployeeId(), "Registered new account");
	    } else {
	        logger.warning("Registration failed for " + emp.getEmployeeId());
	    }
	
	    return result;
    }

}
