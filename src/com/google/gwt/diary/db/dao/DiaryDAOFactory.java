package com.google.gwt.diary.db.dao;

public class DiaryDAOFactory {
	public static DiaryDAO getDiaryDAO() {
		return 	new DiaryDAOImpl();
	}
}
