package com.revature.revworkforce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {
	private static final String URL =
            "jdbc:mysql://localhost:3306/revworkforce?useSSL=false&serverTimezone=UTC";
	
    private static final String USER = "root";       // change if needed
    private static final String PASSWORD = "Mdurgaprasad@554";   // change if needed
    
    private DBConnectionUtil() {
        // Prevent object creation
    }
    

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(" Database connection failed!", e);
        }
    }


}
