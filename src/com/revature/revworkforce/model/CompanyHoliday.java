package com.revature.revworkforce.model;

import java.sql.Date;

public class CompanyHoliday {
	

    private int holidayId;
    private String holidayName;
    private Date holidayDate;
	public CompanyHoliday() {
		super();
	}
	public CompanyHoliday(int holidayId, String holidayName, Date holidayDate) {
		super();
		this.holidayId = holidayId;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
	}
	public int getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
    
    

}
