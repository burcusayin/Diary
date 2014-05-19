package com.google.gwt.diary.db;

//import java.util.UUID; 

import com.google.gwt.diary.db.dao.PersonDAO;
import com.google.gwt.diary.db.dao.PersonDAOFactory;
import com.google.gwt.diary.server.GreetingServiceImpl;

import model.Person;

public class DatabaseController { 
	

	PersonDAO pDao = PersonDAOFactory.getPersonDAO();
	GreetingServiceImpl service;
	public boolean addNewAccount(String username, String password,String name, String surname, String phone, String email, String address)
	{
		String pass = password;
		String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
		//(create new user entry in db storing ONLY username and hash, *NOT* the password).
		System.out.println("Uretilen hash deðeri: " + hash + "\n");

		Person newPerson = new  Person();
		newPerson.setName(name);
		newPerson.setSurname(surname);
		newPerson.setPhone(phone);
		newPerson.setEmail(email);
		newPerson.setAddress(address);
		newPerson.setUsername(username);
		newPerson.setPassword(hash);

		
		int result = pDao.insertPerson(newPerson);
		if (result==1){
			System.out.println("Person is added!!");
			return true;
		}else{ 
			System.out.println("Could not be added!");
			return false;
		}
	}
	

	public String verifyLoginEntries(String username, String password)
	{
		String pass;
		String passwordResult;
		Person person = null;
		String res = null;
		service = new GreetingServiceImpl();
		pass = password;
			
		person = pDao.getPersonByUsername(username);
		passwordResult = person.getPassword();
			
		String hashFromDB = passwordResult;
		boolean valid = BCrypt.checkpw(pass, hashFromDB);
		if (valid)
		{
			//service.setUserName(username);
			res = "Username and password are true ";
		}
		else 
		{
			res = "Wrong username or password!";
		}
	
		return res;
	}
}
