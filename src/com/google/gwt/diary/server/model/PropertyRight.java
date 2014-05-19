package com.google.gwt.diary.server.model;

import java.util.Date;

public class PropertyRight {
	
	int propertyRightId;
	Date date;
	String type;
	DiaryProperties dp;
	int diaryId;
		
	public PropertyRight(int propertyRightId, Date date, String type, DiaryProperties diary) {
		super();
		this.propertyRightId = propertyRightId;
		this.date = date;
		this.type = type;
		dp = new DiaryProperties();
		dp = diary;
		this.diaryId = dp.getDiaryId();
	}
	
	
	public int getPropertyRightId() {
		return propertyRightId;
	}
	public void setPropertyRightId(int propertyRightId) {
		this.propertyRightId = propertyRightId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
