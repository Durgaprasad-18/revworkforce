package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.CompanyHoliday;
import com.revature.revworkforce.util.DBConnectionUtil;

public class CompanyHolidayDAO {
	
	 public static List<CompanyHoliday> getAllHolidays() {

	        List<CompanyHoliday> holidays = new ArrayList<>();

	        String sql = "SELECT * FROM company_holiday ORDER BY holiday_date";

	        try (Connection con = DBConnectionUtil.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                CompanyHoliday h = new CompanyHoliday();
	                h.setHolidayId(rs.getInt("holiday_id"));
	                h.setHolidayDate(rs.getDate("holiday_date"));
	                h.setHolidayName(rs.getString("holiday_name"));
	                holidays.add(h);
	            }

	        } catch (Exception e) {
	        	System.out.println("Error Fetching Holidays: " + e.getMessage());
	        }
	        return holidays;
	    }

}
