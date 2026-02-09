package com.revature.revworkforce.model;
import java.sql.Timestamp;

public class Announcement {

    private int announcementId;
    private String title;
    private String message;
    private Timestamp createdAt;
	public Announcement() {
		super();
	}
	public Announcement(int announcementId, String title, String message, Timestamp createdAt) {
		super();
		this.announcementId = announcementId;
		this.title = title;
		this.message = message;
		this.createdAt = createdAt;
	}
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
    

}
