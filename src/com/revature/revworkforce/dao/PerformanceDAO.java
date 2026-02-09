package com.revature.revworkforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.revworkforce.model.PerformanceReview;
import com.revature.revworkforce.util.DBConnectionUtil;

public class PerformanceDAO {
	

    // Save performance review
    public void submitReview(PerformanceReview review) {

        String sql =
            "INSERT INTO performance_review " +
            "(employee_id, self_rating, status) " +
            "VALUES (?, ?, ?)";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, review.getEmployeeId());
            ps.setInt(2, review.getSelfRating());
            ps.setString(3, review.getStatus());
            ps.executeUpdate();

        } catch (Exception e) {
        	System.out.println("Unable to Submit Review. Please try again.:"+e.getMessage());
        }
    }

    // Get reviews with manager feedback
    public static List<PerformanceReview> getReviewsByEmployee(int employeeId) {

        List<PerformanceReview> list = new ArrayList<>();

        String sql =
            "SELECT manager_rating, manager_feedback " +
            "FROM performance_review WHERE employee_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PerformanceReview r = new PerformanceReview();
                r.setManagerRating(rs.getInt("manager_rating"));
                r.setFeedback(rs.getString("feedback"));
                list.add(r);
            }

        } catch (Exception e) {
        	System.out.println("Unable to Fetch data. Please try again.:"+e.getMessage());
        }
        return list;
    }
    
    public static PerformanceReview getReviewById(int reviewId) {

        String sql =
            "SELECT * FROM performance_review WHERE review_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reviewId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                PerformanceReview review = new PerformanceReview();

                review.setReviewId(rs.getInt("review_id"));
                review.setEmployeeId(rs.getInt("employee_id"));
                review.setSelfRating(rs.getInt("self_rating"));
                review.setManagerRating(rs.getInt("manager_rating"));
                review.setFeedback(rs.getString("feedback"));

                return review;
            }

        } catch (Exception e) {
        	System.out.println("Unable to Fetch Performance Review. Please try again.:"+e.getMessage());
        }

        return null;
    }
    
    public static void updateManagerFeedback(int reviewId,
            int rating,
            String feedback) {

			String sql =
			"UPDATE performance_review " +
			"SET manager_rating=?, feedback=?, status='REVIEWED' " +
			"WHERE review_id=?";
			
			try (Connection con = DBConnectionUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setInt(1, rating);
			ps.setString(2, feedback);
			ps.setInt(3, reviewId);
			
			ps.executeUpdate();
			
			} catch (Exception e) {
				System.out.println("Unable to Update manager Feedback. Please try again.:"+e.getMessage());
		}
    }
    
    
    public List<PerformanceReview> getReviewsByManager(int managerId) {

        List<PerformanceReview> list = new ArrayList<>();

        String sql =
            "SELECT p.* FROM performance_review p " +
            "JOIN employee e ON p.employee_id = e.employee_id " +
            "WHERE e.manager_id=?";

        try (Connection con = DBConnectionUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, managerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PerformanceReview r = new PerformanceReview();

                r.setReviewId(rs.getInt("review_id"));
                r.setEmployeeId(rs.getInt("employee_id"));
                r.setSelfRating(rs.getInt("self_rating"));
                r.setManagerRating(rs.getInt("manager_rating"));
                r.setFeedback(rs.getString("feedback"));
                r.setStatus(rs.getString("status"));

                list.add(r);
            }

        } catch (Exception e) {
        	System.out.println("Unable to Fetch Reviews. Please try again.:"+e.getMessage());
        }

        return list;
    }



}
