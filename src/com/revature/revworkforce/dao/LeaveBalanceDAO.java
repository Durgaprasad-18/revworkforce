package com.revature.revworkforce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.LeaveApplication;
import com.revature.revworkforce.model.LeaveBalance;
import com.revature.revworkforce.util.DBConnectionUtil;

public class LeaveBalanceDAO {
	
	 // GET BALANCE
    public int getRemainingDays(int employeeId, int leaveTypeId) {

        String sql = "SELECT remaining_days FROM leave_balance "
                   + "WHERE employee_id=? AND leave_type_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, leaveTypeId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("remaining_days");
            }

        } catch (Exception e) {
        	System.out.println("Unable to fetch Remaining days. Please try again.:"+e.getMessage());
        }
        return 0;
    }

    // UPDATE BALANCE
    public boolean updateLeaveBalance(int employeeId, int leaveTypeId, int days) {

        String sql = "UPDATE leave_balance SET remaining_days=? "
                   + "WHERE employee_id=? AND leave_type_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, days);
            ps.setInt(2, employeeId);
            ps.setInt(3, leaveTypeId);

            return ps.executeUpdate() == 1;

        } catch (Exception e) {
        	System.out.println("Unable to Update Leave Balance. Please try again.:"+e.getMessage());
        }
        return false;
    }

	public List<LeaveBalance> getLeaveBalances(int employeeId) {
		// TODO Auto-generated method stub
		List<LeaveBalance> list = new ArrayList<>();

        String sql =
            "SELECT * FROM leave_balance WHERE employee_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LeaveBalance lb = new LeaveBalance();
                lb.setEmployeeId(employeeId);
                lb.setLeaveTypeId(rs.getInt("leave_type_id"));
                lb.setRemainingDays(rs.getInt("remaining_days"));
                list.add(lb);
            }

        } catch (Exception e) {
        	System.out.println("Unable to Fetch leave balances. Please try again.:"+e.getMessage());
        }
        return list;
    }
	
	public void assignLeaveBalance(int empId, int leaveTypeId, int days) {

        String sql =
            "INSERT INTO leave_balance (employee_id, leave_type_id, remaining_days) " +
            "VALUES (?, ?, ?) " +
            "ON DUPLICATE KEY UPDATE remaining_days=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ps.setInt(2, leaveTypeId);
            ps.setInt(3, days);
            ps.setInt(4, days);

            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Unable to assign Leaves. Please try again.:"+e.getMessage());
        }
    }
}
