package com.google.gwt.diary.db.dao;

public class PersonDAOFactory {
	public static PersonDAO getPersonDAO() {
		return 	new PersonDAOImpl();
	}
}
