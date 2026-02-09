package com.revature.revworkforce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.Notification;
import com.revature.revworkforce.util.DBConnectionUtil;


public class NotificationDAO {
	
	// Fetch notifications for employee
    public static List<Notification> getNotificationsByEmployee(int employeeId) {

        List<Notification> list = new ArrayList<>();

        String sql =
            "SELECT notification_id, employee_id, message, is_read, created_at " +
            "FROM notification " +
            "WHERE employee_id = ? " +
            "ORDER BY created_at DESC";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notification n = new Notification();
                n.setNotificationId(rs.getInt("notification_id"));
                n.setEmployeeId(rs.getInt("employee_id"));
                n.setMessage(rs.getString("message"));
                n.setRead(rs.getBoolean("is_read"));
                n.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(n);
            }

        } catch (Exception e) {
        	System.out.println("Error occured while recieving notifications. Please try again.:"+e.getMessage());
        }
        return list;
    }

    // Mark notifications as READ
    public static void markAsRead(int employeeId) {

        String sql =
            "UPDATE notification SET is_read = true " +
            "WHERE employee_id = ? AND is_read = false";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Error occured while updating status. Please try again.:"+e.getMessage());
        }
    }

}
