package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.CompanyHoliday;
import com.revature.revworkforce.util.DBConnectionUtil;

public class HolidayDAO {
	
	public void addHoliday(String name, String date) {

        String sql =
            "INSERT INTO company_holiday (holiday_name, holiday_date) VALUES (?, ?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, date);

            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Unable to add Holiday. Please try again.:"+e.getMessage());
        }
    }

    public List<CompanyHoliday> getAllHolidays() {

        List<CompanyHoliday> list = new ArrayList<>();

        String sql = "SELECT * FROM company_holiday";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

            	CompanyHoliday h = new CompanyHoliday();

                h.setHolidayId(rs.getInt("holiday_id"));
                h.setHolidayName(rs.getString("holiday_name"));
                h.setHolidayDate(rs.getDate("holiday_date"));

                list.add(h);
            }

        } catch (Exception e) {
        	System.out.println("Error occured while fetching data. Please try again.:"+e.getMessage());
        }

        return list;
    }

}
