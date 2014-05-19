package com.google.gwt.diary.db.dao;

import model.Person;


public interface PersonDAO {
	public int insertPerson(Person newPerson);
	public int deletePerson(String uname);
	public Person getPersonByUsername(String uname);
}
