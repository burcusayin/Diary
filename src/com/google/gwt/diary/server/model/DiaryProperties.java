package com.google.gwt.diary.server.model;

public class DiaryProperties {
	
	String date;
	String time;
	String title;
	String content;
	int diaryId;
	
	public DiaryProperties( String date, String time, String title, int diaryId, String content)
	{
		this.date = date;
		this.time = time;
		this.title = title;
		this.diaryId = diaryId;
		this.content = content;
		
	}
	
	public DiaryProperties(String content)
	{
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
