package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.Designation;
import com.revature.revworkforce.util.DBConnectionUtil;

public class DesignationDAO {
	

    public void addDesignation(String name) {

        String sql =
            "INSERT INTO designation (designation_name) VALUES (?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Error occured while inserting Designation: " + e.getMessage());
        }
    }

    public List<Designation> getAllDesignations() {

        List<Designation> list = new ArrayList<>();

        String sql = "SELECT * FROM designation";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Designation d = new Designation();

                d.setDesignationId(rs.getInt("designation_id"));
                d.setDesignationName(rs.getString("designation_name"));

                list.add(d);
            }

        } catch (Exception e) {
        	System.out.println("Error occured While Fetching Designations: " + e.getMessage());
        }

        return list;
    }

}
