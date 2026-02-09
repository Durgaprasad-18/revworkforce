package com.revature.revworkforce.model;

public class PerformanceReview {
	 	private int reviewId;
	    private int employeeId;
	    private int reviewYear;
	    private int selfRating;
	    private int managerRating;
	    private String feedback;
	    private String status;
		public PerformanceReview() {
			super();
		}
		public PerformanceReview(int reviewId, int employeeId, int reviewYear, int selfRating, int managerRating,
				String feedback, String status) {
			super();
			this.reviewId = reviewId;
			this.employeeId = employeeId;
			this.reviewYear = reviewYear;
			this.selfRating = selfRating;
			this.managerRating = managerRating;
			this.feedback = feedback;
			this.status = status;
		}
		public int getReviewId() {
			return reviewId;
		}
		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public int getReviewYear() {
			return reviewYear;
		}
		public void setReviewYear(int reviewYear) {
			this.reviewYear = reviewYear;
		}
		public int getSelfRating() {
			return selfRating;
		}
		public void setSelfRating(int selfRating) {
			this.selfRating = selfRating;
		}
		public int getManagerRating() {
			return managerRating;
		}
		public void setManagerRating(int managerRating) {
			this.managerRating = managerRating;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
	    
	    
}
