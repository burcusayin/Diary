package com.google.gwt.diary.db.test;

import model.Person;

import com.google.gwt.diary.db.dao.PersonDAO;
import com.google.gwt.diary.db.dao.PersonDAOFactory;

public class TestPerson {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PersonDAO pDao = PersonDAOFactory.getPersonDAO();
		
		
		///// insertion
		Person newPerson = new  Person();

		newPerson.setUsername("pelin");
		newPerson.setPassword("150");
		newPerson.setName("pelin");
		newPerson.setSurname("el");
		newPerson.setPhone("0538");
		newPerson.setEmail("pelinel@std.iyte.edu.tr");
		newPerson.setAddress("UÞAK");


		
		int result = pDao.insertPerson(newPerson);
		if (result==1){
			System.out.println("person added!!");
		}else{ 
			System.out.println("Can not be added");
		}
		
		
		
		
		/*
	    ///// selection
		Person person = null;
		person = pDao.getPersonByUsername("ayse");
		
		if(person != null){
			System.out.println("person username : " + person.getUsername());
			System.out.println("person password : " + person.getPassword());
			System.out.println("person name : " + person.getName());
			System.out.println("person surname : " + person.getSurname());
			System.out.println("person phone : " + person.getPhone());
			System.out.println("person email : " + person.getEmail());

		}else{
			System.out.println("there is no person for this username");
		}
		*/
		
		
		/*
		int result2 = pDao.deletePerson("pelin");
		if (result2==1){ 
			System.out.println("person deleted!!");
		}else{ 
			System.out.println("there is no person for this username");
		}
		*/
	}
}
