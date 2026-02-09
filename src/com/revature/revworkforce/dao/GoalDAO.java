package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revature.revworkforce.util.DBConnectionUtil;
import com.revature.revworkforce.model.Goal;

public class GoalDAO {
	
	// Insert goal
	public static boolean createGoal(Goal goal) {

	    String sql =
	        "INSERT INTO goal " +
	        "(employee_id, description, deadline, priority, progress) " +
	        "VALUES (?, ?, ?, ?, ?)";

	    try (Connection con = DBConnectionUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, goal.getEmployeeId());
	        ps.setString(2, goal.getDescription());
	        ps.setDate(3, goal.getDeadline());   //  Direct use
	        ps.setString(4, goal.getPriority());
	        ps.setInt(5, goal.getProgress());

	        return ps.executeUpdate() > 0;

	    } catch (Exception e) {
	    	System.out.println("Unable to create Goal. Please try again.:"+e.getMessage());
	    }

	    return false;
	}




    // Update progress
    public static void updateGoalProgress(int goalId, int progress) {

        String sql =
            "UPDATE goal SET progress=? WHERE goal_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, progress);
            ps.setInt(2, goalId);
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Unable to Update Goal. Please try again.:"+e.getMessage());
        }
    }

}
