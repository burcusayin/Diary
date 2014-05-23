package com.google.gwt.diary.db;

//import java.util.UUID; 

import java.util.Calendar;

import com.google.gwt.diary.db.dao.DiaryDAO;
import com.google.gwt.diary.db.dao.DiaryDAOFactory;
import com.google.gwt.diary.db.dao.PersonDAO;
import com.google.gwt.diary.db.dao.PersonDAOFactory;
import com.google.gwt.diary.db.dao.PropertyrightDAO;
import com.google.gwt.diary.db.dao.PropertyrightDAOFactory;
import com.google.gwt.diary.db.dao.PropertyrightholderDAO;
import com.google.gwt.diary.db.dao.PropertyrightholderDAOFactory;
import com.google.gwt.diary.server.GreetingServiceImpl;

import model.Person;
import model.Propertyright;
import model.Propertyrightholder;

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
	
	public Boolean addNewHolder(Long id, String name, String surname,String email,String phone){
		
		PropertyrightholderDAO prhDao = PropertyrightholderDAOFactory.getPropertyrightholderDAO();
		Propertyrightholder holder = new Propertyrightholder();
		holder.setHolderId(id);
		holder.setHolderName(name);
		holder.setHolderSurname(surname);
		holder.setHolderEMail(email);
		holder.setHolderPhone(phone);

		
		int result = prhDao.insertPropertyrightholder(holder);
		if (result==1){
			System.out.println("Holder is added!!");
			return true;
		}else{ 
			System.out.println("Holder could not be added!");
			return false;
		}
	}
	
	public Boolean addNewPropertyright(Long id, String type){
		
		PropertyrightDAO prDao = PropertyrightDAOFactory.getPropertyRightDAO();
		DiaryDAO dDAO = DiaryDAOFactory.getDiaryDAO();
		
		Propertyright propertyright = new Propertyright();
		propertyright.setPropertyrightId(id);
		propertyright.setPropertyrightDate(Calendar.getInstance().getTime());
		propertyright.setType(type);
		propertyright.setDiary(dDAO.getDiaryById(dDAO.getMaxDiaryId()));
	

		
		int result = prDao.insertPropertyright(propertyright);
		if (result==1){
			System.out.println("Propertyright is added!!");
			return true;
		}else{ 
			System.out.println("Propertyright could not be added!");
			return false;
		}
	}
}
