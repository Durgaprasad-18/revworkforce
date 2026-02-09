package com.revature.revworkforce.service;

import java.sql.Date;
import java.util.logging.Logger;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.GoalDAO;
import com.revature.revworkforce.model.Goal;
import com.revature.revworkforce.util.LoggerUtil;

public class GoalService {
	
	private final Logger logger = LoggerUtil.getLogger();
    private final AuditLogDAO auditLogDAO = new AuditLogDAO();
    private final GoalDAO goalDAO = new GoalDAO();

    public boolean createGoal(int employeeId,
            String description,
            Date deadline,
            String priority) {
			
			Goal goal = new Goal();
			goal.setEmployeeId(employeeId);
			goal.setDescription(description);
			goal.setDeadline(deadline);
			goal.setPriority(priority);
			goal.setProgress(0);
			
			boolean result = goalDAO.createGoal(goal);
			
			if (result) {
			logger.info("Goal created for employeeId " + employeeId);
			auditLogDAO.logAction(employeeId, "Created a goal");
			}
			
			return result;
			}



    public void updateGoal(int employeeId, int goalId) {

        logger.info("Goal updated: " + goalId);
        auditLogDAO.logAction(employeeId, "Updated goal " + goalId);
    }

	public void updateGoalProgress(int employeeId, int goalId, int progress) {
		// TODO Auto-generated method stub
		GoalDAO.updateGoalProgress(goalId, progress);

        logger.info("Goal " + goalId + " updated to " + progress + "%");
        auditLogDAO.logAction(
                employeeId,
                "Updated goal " + goalId + " progress to " + progress + "%"
        );
		
	}

}
