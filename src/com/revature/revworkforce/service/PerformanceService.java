package com.revature.revworkforce.service;

import java.util.List;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.PerformanceDAO;
import com.revature.revworkforce.model.PerformanceReview;
import com.revature.revworkforce.util.LoggerUtil;

public class PerformanceService {
    private final Logger logger = LoggerUtil.getLogger();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();
    //private final PerformanceDAO performanceDAO = new PerformanceDAO();
    //private final NotificationService notificationService = new NotificationService();

    
    public void submitReview(int employeeId) {

        logger.info("Performance review submitted by " + employeeId);
        auditLogDAO.logAction(employeeId, "Submitted performance review");
    }

    public void reviewByManager(int managerId, int employeeId) {

        logger.info("Manager " + managerId + " reviewed employee " + employeeId);
        auditLogDAO.logAction(
                managerId,
                "Reviewed performance of employee " + employeeId);
    }

	public void viewManagerFeedback(int employeeId) {
		// TODO Auto-generated method stub
		
		 List<PerformanceReview> reviews =
	                PerformanceDAO.getReviewsByEmployee(employeeId);

	        if (reviews.isEmpty()) {
	            System.out.println("No performance feedback available.");
	            return;
	        }

	        System.out.println("\n--- Manager Feedback ---");
	        for (PerformanceReview r : reviews) {
	            System.out.println(
	                "Rating: " + r.getManagerRating() +
	                " | Feedback: " + r.getFeedback()
	            );
	        }

	        logger.info("Viewed performance feedback for employeeId " + employeeId);
	        auditLogDAO.logAction(employeeId, "Viewed performance feedback");
		
	}
	
	public PerformanceReview getReviewDetails(int reviewId) {

	    logger.info("Fetching review details for reviewId " + reviewId);

	    return PerformanceDAO.getReviewById(reviewId);
	}
	
	public void giveManagerFeedback(int reviewId,
            int rating,
            String feedback,
            int managerId) {

			PerformanceDAO.updateManagerFeedback(reviewId, rating, feedback);
			
			PerformanceReview review =
			PerformanceDAO.getReviewById(reviewId);
			
			//  Notify Employee
			NotificationService.sendNotification(
			review.getEmployeeId(),
			"Manager reviewed your performance review"
			);
			
			logger.info("Manager " + managerId +
			" submitted feedback for review " + reviewId);
}


}
