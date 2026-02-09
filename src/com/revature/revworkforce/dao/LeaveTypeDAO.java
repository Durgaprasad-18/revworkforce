package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.LeaveType;
import com.revature.revworkforce.util.DBConnectionUtil;

public class LeaveTypeDAO {
	
	 public void addLeaveType(int adminId,String name, int maxDays) {

	        String sql =
	            "INSERT INTO leave_type (leave_name, max_days) VALUES (?, ?)";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, name);
	            ps.setInt(2, maxDays);

	            ps.executeUpdate();

	        } catch (Exception e) {
	        	System.out.println("Unable to add Leave Type. Please try again.:"+e.getMessage());
	        }
	    }

	    public List<LeaveType> getAllLeaveTypes() {

	        List<LeaveType> list = new ArrayList<>();

	        String sql = "SELECT * FROM leave_type";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {

	                LeaveType lt = new LeaveType();

	                lt.setLeaveTypeId(rs.getInt("leave_type_id"));
	                lt.setLeaveName(rs.getString("leave_name"));
	                lt.setMaxDays(rs.getInt("max_days"));

	                list.add(lt);
	            }

	        } catch (Exception e) {
	        	System.out.println("Unable to Fetch Leave Types. Please try again.:"+e.getMessage());
	        }

	        return list;
	    }

}
