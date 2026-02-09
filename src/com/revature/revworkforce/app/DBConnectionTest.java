package com.revature.revworkforce.app;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.revature.revworkforce.util.DBConnectionUtil;

public class DBConnectionTest {
	
	public static void main(String[] args) {

        try (Connection con = DBConnectionUtil.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1")) {

            if (rs.next()) {
                System.out.println(" Database connection SUCCESSFUL");
            }

        } catch (Exception e) {
            System.out.println(" Database connection FAILED");
            e.printStackTrace();
        }
    }

}
