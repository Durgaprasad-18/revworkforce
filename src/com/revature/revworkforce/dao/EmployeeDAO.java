package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.Employee;
import com.revature.revworkforce.util.DBConnectionUtil;

public class EmployeeDAO {
	
	 // LOGIN EMPLOYEE
    public Employee login(int employeeId, String password) {

        String sql = "SELECT * FROM employee WHERE employee_id=? AND password=? AND status='ACTIVE'";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setRole(rs.getString("role"));
                emp.setManagerId(rs.getInt("manager_id"));
                emp.setStatus(rs.getString("status"));
                return emp;
            }

        } catch (Exception e) {
        	System.out.println("Unable to login. Please try again."+e.getMessage());
        }
        return null;
    }

    // REGISTER Employee
    public boolean register(Employee emp) {

        String sql = "INSERT INTO employee (employee_id, name, email, password, role, manager_id,security_question,security_answer) "
                   + "VALUES (?, ?, ?, ?, ?, ?,?,?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, emp.getEmployeeId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPassword());
            ps.setString(5, emp.getRole());
            ps.setObject(6, emp.getManagerId());
            ps.setString(7, emp.getSecurityQuestion());
            ps.setString(8, emp.getSecurityAnswer());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
        	System.out.println("Unable to Register. Please try again."+e.getMessage());
        }
        return false;
    }

	public boolean updateProfile(int employeeId, String phone, String address, String emergencyContact) {
		// TODO Auto-generated method stub
		String sql =
	            "UPDATE employee " +
	            "SET phone = ?, address = ?, emergency_contact = ? " +
	            "WHERE employee_id = ?";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, phone);
	            ps.setString(2, address);
	            ps.setString(3, emergencyContact);
	            ps.setInt(4, employeeId);

	            return ps.executeUpdate() == 1;

	        } catch (Exception e) {
	        	System.out.println("Unable to Update profile. Please try again.:"+e.getMessage());
	        }
		return false;
	}

	public Employee getManagerDetails(int employeeId) {
		// TODO Auto-generated method stub
		String sql =
		        "SELECT m.employee_id, m.name, m.email, m.phone " +
		        "FROM employee e " +
		        "JOIN employee m ON e.manager_id = m.employee_id " +
		        "WHERE e.employee_id = ?";

		    try (Connection con = DBConnectionUtil.getConnection();
		         PreparedStatement ps = con.prepareStatement(sql)) {

		        ps.setInt(1, employeeId);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            Employee manager = new Employee();
		            manager.setEmployeeId(rs.getInt("employee_id"));
		            manager.setName(rs.getString("name"));
		            manager.setEmail(rs.getString("email"));
		            manager.setPhone(rs.getString("phone"));
		            return manager;
		        }

		    } catch (Exception e) {
		    	System.out.println("Unable to get Manager Details. Please try again.:"+e.getMessage());
		    }
		    return null;
	}
	
	public int getManagerId(int employeeId) {

	    String sql =
	        "SELECT manager_id FROM employee WHERE employee_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getInt("manager_id");
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to fetch ManagerId. Please try again.:"+e.getMessage());
	    }

	    return -1;
	}
	
	public List<Employee> getEmployeesByManager(int managerId) {

	    List<Employee> list = new ArrayList<>();

	    String sql =
	        "SELECT * FROM employee WHERE manager_id=? AND status='ACTIVE'";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, managerId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Employee emp = new Employee();

	            emp.setEmployeeId(rs.getInt("employee_id"));
	            emp.setName(rs.getString("name"));
	            emp.setEmail(rs.getString("email"));
	            emp.setRole(rs.getString("role"));
	            emp.setManagerId(rs.getInt("manager_id"));
	            emp.setStatus(rs.getString("status"));

	            list.add(emp);
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Fetch Employees. Please try again.:"+e.getMessage());
	    }

	    return list;
	}
	
	public List<Employee> getAllEmployees() {

	    List<Employee> list = new ArrayList<>();

	    String sql = "SELECT * FROM employee";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            Employee emp = new Employee();

	            emp.setEmployeeId(rs.getInt("employee_id"));
	            emp.setName(rs.getString("name"));
	            emp.setEmail(rs.getString("email"));
	            emp.setRole(rs.getString("role"));
	            emp.setManagerId(rs.getInt("manager_id"));
	            emp.setStatus(rs.getString("status"));

	            list.add(emp);
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Fetch employees. Please try again.:"+e.getMessage());
	    }

	    return list;
	}
	
	public void updateEmployeeStatus(int employeeId, String status) {

	    String sql =
	        "UPDATE employee SET status=? WHERE employee_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, status);
	        ps.setInt(2, employeeId);

	        ps.executeUpdate();

	    } catch (Exception e) {
	    	System.out.println("Unable to Update Employee status. Please try again.:"+e.getMessage());
	    }
	}
	
	public void assignManager(int employeeId, int managerId) {

	    String sql =
	        "UPDATE employee SET manager_id=? WHERE employee_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, managerId);
	        ps.setInt(2, employeeId);

	        ps.executeUpdate();

	    } catch (Exception e) {
	    	System.out.println("Unable to assign manager. Please try again.:"+e.getMessage());
	    }
	}
	
	
	public List<Employee> searchEmployees(String keyword) {

	    List<Employee> list = new ArrayList<>();

	    String sql =
	        "SELECT * FROM employee " +
	        "WHERE CAST(employee_id AS CHAR) LIKE ? " +
	        "OR name LIKE ? " +
	        "OR role LIKE ? " +
	        "OR email LIKE ?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        String search = "%" + keyword + "%";

	        ps.setString(1, search);
	        ps.setString(2, search);
	        ps.setString(3, search);
	        ps.setString(4, search);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Employee emp = new Employee();

	            emp.setEmployeeId(rs.getInt("employee_id"));
	            emp.setName(rs.getString("name"));
	            emp.setEmail(rs.getString("email"));
	            emp.setRole(rs.getString("role"));
	            emp.setStatus(rs.getString("status"));

	            list.add(emp);
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Search Employee. Please try again.:"+e.getMessage());
	    }

	    return list;
	   
	}
	public boolean verifySecurityAnswer(
	        int employeeId,
	        String answer) {

	    String sql =
	        "SELECT * FROM employee " +
	        "WHERE employee_id=? AND security_answer=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, employeeId);
	        ps.setString(2, answer);

	        ResultSet rs = ps.executeQuery();

	        return rs.next();

	    } catch (Exception e) {
	        System.out.println("Verification failed: " + e.getMessage());
	    }

	    return false;
	}
	
	public boolean updatePassword(int employeeId, String newPassword) {

	    String sql =
	        "UPDATE employee SET password=? WHERE employee_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, newPassword);
	        ps.setInt(2, employeeId);

	        return ps.executeUpdate() == 1;

	    } catch (Exception e) {
	        System.out.println("Password reset failed: " + e.getMessage());
	    }

	    return false;
	}



}
