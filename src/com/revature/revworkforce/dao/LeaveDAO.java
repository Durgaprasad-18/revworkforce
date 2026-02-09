package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.util.DBConnectionUtil;


public class LeaveDAO {
	

    // APPLY LEAVE
    public boolean applyLeave(LeaveApplication leave) {

        String sql = "INSERT INTO leave_application "
                   + "(employee_id, leave_type_id, start_date, end_date, reason) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, leave.getEmployeeId());
            ps.setInt(2, leave.getLeaveTypeId());
            ps.setString(3, leave.getStartDate());
            ps.setString(4, leave.getEndDate());
            ps.setString(5, leave.getReason());

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
        	System.out.println("Unable to apply Leave. Please try again.:"+e.getMessage());
        }
        return false;
    }

    // VIEW LEAVES BY EMPLOYEE
    public List<LeaveApplication> getLeavesByEmployee(int employeeId) {

        List<LeaveApplication> leaves = new ArrayList<>();

        String sql = "SELECT * FROM leave_application WHERE employee_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LeaveApplication leave = new LeaveApplication();
                leave.setLeaveId(rs.getInt("leave_id"));
                leave.setEmployeeId(rs.getInt("employee_id"));
                leave.setLeaveTypeId(rs.getInt("leave_type_id"));
                leave.setStartDate(rs.getString("start_date"));
                leave.setEndDate(rs.getString("end_date"));
                leave.setReason(rs.getString("reason"));
                leave.setStatus(rs.getString("status"));
                leave.setManagerComment(rs.getString("manager_comment"));
                leaves.add(leave);
            }

        } catch (Exception e) {
        	System.out.println("Unable to Fetch leave application. Please try again.:"+e.getMessage());
        }
        return leaves;
    }

	public boolean cancelLeave(int employeeId, int leaveId) {
		// TODO Auto-generated method stub
		String sql =
	            "UPDATE leave_application " +
	            "SET status='CANCELLED' " +
	            "WHERE leave_id=? AND employee_id=? AND status='PENDING'";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setInt(1, leaveId);
	            ps.setInt(2, employeeId);

	            return ps.executeUpdate() == 1;

	        } catch (Exception e) {
	        	System.out.println("Unable to Cancel Leave. Please try again.:"+e.getMessage());
	        }
	        return false;
	}
	
	public LeaveApplication getLeaveById(int leaveId) {

	    String sql = "SELECT * FROM leave_application WHERE leave_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, leaveId);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            LeaveApplication leave = new LeaveApplication();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmployeeId(rs.getInt("employee_id"));
	            leave.setLeaveTypeId(rs.getInt("leave_type_id"));
	            leave.setStartDate(rs.getString("start_date"));
	            leave.setEndDate(rs.getString("end_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));
	            leave.setManagerComment(rs.getString("manager_comment"));

	            return leave;
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Fetch Leave. Please try again.:"+e.getMessage());
	    }

	    return null;
	}

	
	public static void updateLeaveStatus(int leaveId, String status) {

	    String sql =
	        "UPDATE leave_application SET status=? WHERE leave_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, status);
	        ps.setInt(2, leaveId);

	        ps.executeUpdate();

	    } catch (Exception e) {
	    	System.out.println("Unable to Update Leave Status. Please try again.:"+e.getMessage());
	    }
	}
	
	
	public List<LeaveApplication> getLeavesByManager(int managerId) {

	    List<LeaveApplication> list = new ArrayList<>();

	    String sql =
	        "SELECT l.* FROM leave_application l " +
	        "JOIN employee e ON l.employee_id = e.employee_id " +
	        "WHERE e.manager_id=?";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, managerId);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            LeaveApplication leave = new LeaveApplication();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmployeeId(rs.getInt("employee_id"));
	            leave.setLeaveTypeId(rs.getInt("leave_type_id"));
	            leave.setStartDate(rs.getString("start_date"));
	            leave.setEndDate(rs.getString("end_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));
	            leave.setManagerComment(rs.getString("manager_comment"));

	            list.add(leave);
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Fetch data. Please try again.:"+e.getMessage());
	    }

	    return list;
	}
	
	
	public static List<LeaveApplication> getAllLeaves() {

	    List<LeaveApplication> list = new ArrayList<>();

	    String sql = "SELECT * FROM leave_application";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            LeaveApplication leave = new LeaveApplication();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmployeeId(rs.getInt("employee_id"));
	            leave.setLeaveTypeId(rs.getInt("leave_type_id"));
	            leave.setStartDate(rs.getString("start_date"));
	            leave.setEndDate(rs.getString("end_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));
	            leave.setManagerComment(rs.getString("manager_comment"));

	            list.add(leave);
	        }

	    } catch (Exception e) {
	    	System.out.println("Unable to Fetch leaves. Please try again.:"+e.getMessage());
	    }

	    return list;
	}



}
