package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.Department;
import com.revature.revworkforce.util.DBConnectionUtil;

public class DepartmentDAO {

    public void addDepartment(String deptName) {

        String sql =
            "INSERT INTO department (department_name) VALUES (?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, deptName);
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Error occured while adding department: " + e.getMessage());
        }
    }

    public List<Department> getAllDepartments() {

        List<Department> list = new ArrayList<>();

        String sql = "SELECT * FROM department";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Department d = new Department();

                d.setDepartmentId(rs.getInt("department_id"));
                d.setDepartmentName(rs.getString("department_name"));

                list.add(d);
            }

        } catch (Exception e) {
        	System.out.println("Error occured Fetching Department: " + e.getMessage());
        }

        return list;
    }
}
