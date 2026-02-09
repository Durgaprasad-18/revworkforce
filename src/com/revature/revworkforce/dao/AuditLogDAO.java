package com.revature.revworkforce.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.AuditLog;
import com.revature.revworkforce.util.DBConnectionUtil;

public class AuditLogDAO {
	

    public void logAction(int employeeId, String action) {

        String sql =
            "INSERT INTO audit_log (employee_id, action) VALUES (?, ?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setString(2, action);
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Audit Log Error: " + e.getMessage());
        }
    }
    
    public List<AuditLog> getAllLogs() {

        List<AuditLog> list = new ArrayList<>();

        String sql = "SELECT * FROM audit_log ORDER BY log_time DESC";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                AuditLog log = new AuditLog();

                log.setLogId(rs.getInt("log_id"));
                log.setEmployeeId(rs.getInt("employee_id"));
                log.setAction(rs.getString("action"));
                log.setLogTime(rs.getTimestamp("log_time"));

                list.add(log);
            }

        } catch (Exception e) {
        	System.out.println("Error fetching audit logs: " + e.getMessage());
        }

        return list;
    }


}
