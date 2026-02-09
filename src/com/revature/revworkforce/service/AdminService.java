package com.revature.revworkforce.service;
import java.util.List;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.DepartmentDAO;
import com.revature.revworkforce.dao.DesignationDAO;
import com.revature.revworkforce.dao.EmployeeDAO;
import com.revature.revworkforce.dao.HolidayDAO;
import com.revature.revworkforce.dao.LeaveBalanceDAO;
import com.revature.revworkforce.dao.LeaveDAO;
import com.revature.revworkforce.dao.LeaveTypeDAO;
import com.revature.revworkforce.model.Department;
import com.revature.revworkforce.model.Designation;
import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.model.CompanyHoliday;
import com.revature.revworkforce.model.LeaveType;
import com.revature.revworkforce.util.LoggerUtil;

public class AdminService {
	
    private final Logger logger = LoggerUtil.getLogger();

    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private DesignationDAO designationDAO = new DesignationDAO();
    private LeaveTypeDAO leaveTypeDAO = new LeaveTypeDAO();
    private LeaveBalanceDAO leaveBalanceDAO = new LeaveBalanceDAO();
    private HolidayDAO holidayDAO = new HolidayDAO();
    private AuditLogDAO auditLogDAO = new AuditLogDAO();
    
 // ================= EMPLOYEE SEARCH =================

    public List<Employee> searchEmployees(String keyword) {

        logger.info("Admin searching employees with keyword: " + keyword);

        return employeeDAO.searchEmployees(keyword);
    }
    
 // ================= DEPARTMENT =================
    public void addDepartment(int adminId, String deptName) {

        departmentDAO.addDepartment(deptName);

        logger.info("Department added: " + deptName);

        auditLogDAO.logAction(adminId,
                "Admin added department " + deptName);
    }
    
    
    public void viewDepartments() {

        List<Department> list = departmentDAO.getAllDepartments();

        System.out.println("\n--- Departments ---");

        for (Department d : list) {
            System.out.println(d.getDepartmentId() + " | " + d.getDepartmentName());
        }
    }
    
 // ================= DESIGNATION =================

    public void addDesignation(String designationName) {

        designationDAO.addDesignation(designationName);

        logger.info("Designation added: " + designationName);
        auditLogDAO.logAction(0, "Admin added designation " + designationName);
    }
    
    public void viewDesignations() {

        List<Designation> list = designationDAO.getAllDesignations();

        System.out.println("\n--- Designations ---");

        for (Designation d : list) {
            System.out.println(d.getDesignationId() + " | " + d.getDesignationName());
        }
    }
    
 // ================= LEAVE TYPE =================

    public void addLeaveType(int adminId, String name, int maxDays) {

        leaveTypeDAO.addLeaveType(adminId,name, maxDays);

        logger.info("Leave Type added: " + name);
        auditLogDAO.logAction(adminId, "Admin added leave type " + name);
    }
    
    public void viewLeaveTypes() {

        List<LeaveType> list = leaveTypeDAO.getAllLeaveTypes();

        System.out.println("\n--- Leave Types ---");

        for (LeaveType lt : list) {
            System.out.println(
                    lt.getLeaveTypeId() + " | " +
                    lt.getLeaveName() + " | Limit: " +
                    lt.getMaxDays()
            );
        }
    }
    
 // ================= LEAVE QUOTA =================

    public void assignLeaveQuota(int adminId, int empId, int leaveTypeId, int days) {

        leaveBalanceDAO.assignLeaveBalance(empId, leaveTypeId, days);

        logger.info("Leave quota assigned to emp " + empId);
        auditLogDAO.logAction(adminId, "Admin assigned leave quota"+empId);
    }
    
 // ================= HOLIDAYS =================

    public void addHoliday(int adminId, String name, String date) {

        holidayDAO.addHoliday(name, date);

        logger.info("Holiday added: " + name);
        auditLogDAO.logAction(adminId, "Admin added holiday " + name);
    }
    
    public void viewHolidays() {

        List<CompanyHoliday> list = holidayDAO.getAllHolidays();

        System.out.println("\n--- Holidays ---");

        for (CompanyHoliday h : list) {
            System.out.println(
                    h.getHolidayId() + " | " +
                    h.getHolidayName() + " | " +
                    h.getHolidayDate()
            );
        }
    }

    public void deactivateEmployee(int adminId, int employeeId) {

        logger.warning("Employee " + employeeId + " deactivated by admin " + adminId);
        auditLogDAO.logAction(
                adminId,
                "Deactivated employee " + employeeId);
    }

	public boolean addEmployee(int id, String name, String email, String password, String role, int managerId) {
		// TODO Auto-generated method stub
		Employee emp = new Employee();

        emp.setEmployeeId(id);
        emp.setName(name);
        emp.setEmail(email);
        emp.setPassword(password);
        emp.setRole(role.toUpperCase());
        emp.setManagerId(managerId == 0 ? null : managerId);

        boolean result = employeeDAO.register(emp);

        if (result) {
            logger.info("Admin added employee " + id);
            auditLogDAO.logAction(id, "Employee account created");
        }

		return result;
	}
	 // GET ALL EMPLOYEES
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		logger.info("Admin viewing all employees");

        return employeeDAO.getAllEmployees();

	}
	
	// UPDATE EMPLOYEE STATUS

	public void updateEmployeeStatus(int empId, String status) {
		// TODO Auto-generated method stub
		 employeeDAO.updateEmployeeStatus(empId, status);

	        logger.info("Updated status for employee " + empId);
	        auditLogDAO.logAction(empId,
	               "Status changed to " + status);
		
	}
	// ASSIGN MANAGER
	public void assignManager(int empId, int managerId) {
		// TODO Auto-generated method stub
		employeeDAO.assignManager(empId, managerId);

        logger.info("Assigned manager " + managerId +
                " to employee " + empId);

        auditLogDAO.logAction(empId,
                "Manager assigned: " + managerId);
		
	}
	// VIEW ALL LEAVES
	public List<LeaveApplication> getAllLeaves() {
		// TODO Auto-generated method stub
		logger.info("Admin viewing all leave applications");

        return LeaveDAO.getAllLeaves();
	}
	// ADMIN CANCEL LEAVE
	public void cancelLeaveOverride(int leaveId) {
		// TODO Auto-generated method stub
		
		  LeaveDAO.updateLeaveStatus(leaveId, "CANCELLED");

	      logger.info("Admin cancelled leave " + leaveId);
		
	}
	  // VIEW AUDIT LOGS
	public void viewAuditLogs() {
		// TODO Auto-generated method stub
		
		 System.out.println("\n--- Audit Logs ---");

	        auditLogDAO.getAllLogs()
	                .forEach(log ->
	                        System.out.println(
	                                log.getEmployeeId() +
	                                " | " + log.getAction() +
	                                " | " + log.getLogTime()));
		
	}

}
