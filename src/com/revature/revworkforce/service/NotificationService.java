package com.revature.revworkforce.service;

import java.util.logging.Logger;
import java.util.List;

import com.revature.revworkforce.dao.AuditLogDAO;
import com.revature.revworkforce.dao.NotificationDAO;
import com.revature.revworkforce.model.Notification;
import com.revature.revworkforce.util.LoggerUtil;

public class NotificationService {
	

    private final static Logger logger = LoggerUtil.getLogger();
    private final static AuditLogDAO auditLogDAO = new AuditLogDAO();

    public static void sendNotification(int employeeId, String message) {

        logger.info("Notification sent to " + employeeId + ": " + message);
        auditLogDAO.logAction(employeeId, "Notification sent");
    }

	public void viewNotifications(int employeeId) {
		// TODO Auto-generated method stub
		List<Notification> notifications =
                NotificationDAO.getNotificationsByEmployee(employeeId);

        if (notifications.isEmpty()) {
            System.out.println("No notifications available.");
            return;
        }

        System.out.println("\n--- Notifications ---");
        for (Notification n : notifications) {
            System.out.println(
                (n.isRead() ? "[READ] " : "[UNREAD] ") +
                n.getCreatedAt() + " | " +
                n.getMessage()
            );
        }

        // Mark all as read after viewing
        NotificationDAO.markAsRead(employeeId);

        logger.info("Viewed notifications for employeeId " + employeeId);
        auditLogDAO.logAction(employeeId, "Viewed notifications");
    }
		
	}
