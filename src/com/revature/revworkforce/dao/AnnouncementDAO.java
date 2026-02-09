package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.Announcement;
import com.revature.revworkforce.util.DBConnectionUtil;

public class AnnouncementDAO {
	
	 public static List<Announcement> getAllAnnouncements() {

	        List<Announcement> announcements = new ArrayList<>();

	        String sql =
	            "SELECT announcement_id, title, message, created_at " +
	            "FROM announcement " +
	            "ORDER BY created_at DESC";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Announcement a = new Announcement();
	                a.setAnnouncementId(rs.getInt("announcement_id"));
	                a.setTitle(rs.getString("title"));
	                a.setMessage(rs.getString("message"));
	                a.setCreatedAt(rs.getTimestamp("created_at"));
	                announcements.add(a);
	            }

	        } catch (Exception e) {
	        	System.out.println("Error: " + e.getMessage());
	        }
	        return announcements;
	    }

}
