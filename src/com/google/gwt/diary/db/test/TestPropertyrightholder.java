package com.google.gwt.diary.db.test;

import model.Propertyrightholder;
import com.google.gwt.diary.db.dao.PropertyrightholderDAO;
import com.google.gwt.diary.db.dao.PropertyrightholderDAOFactory;

public class TestPropertyrightholder {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		PropertyrightholderDAO prhDao = PropertyrightholderDAOFactory.getPropertyrightholderDAO();
		
		
		///// insertion
		Propertyrightholder newPropertyrightholder = new Propertyrightholder();
			
		newPropertyrightholder.setHolderId(1);
		newPropertyrightholder.setHolderName("MEHMET");
		newPropertyrightholder.setHolderSurname("TEKÝN");
		newPropertyrightholder.setHolderEMail("mehmettekin@std.iyte.edu.tr");
		newPropertyrightholder.setHolderPhone("0533");
		
		int result = prhDao.insertPropertyrightholder(newPropertyrightholder);
		if (result==1){
			System.out.println("propertyrightholder added!!");
		}else{ 
			System.out.println("Can not be added");
		}
		
		
		/*
	    ///// selection
		Propertyrightholder propertyrightholder = null;
		propertyrightholder = prhDao.getPropertyrightholderById(1);
		
		if(propertyrightholder != null){
			System.out.println("propertyrightholder id : " + propertyrightholder.getHolderId());
			System.out.println("propertyrightholder name : " + propertyrightholder.getHolderName());
			System.out.println("propertyrightholder surname : " + propertyrightholder.getHolderSurname());
			System.out.println("propertyrightholder email : " + propertyrightholder.getHolderEMail());
			System.out.println("propertyrightholder phone : " + propertyrightholder.getHolderPhone());
		}else{
			System.out.println("there is no propertyrightholder for this id");
		}
		*/
		
		
		/*
		///// deletion
		int result2 = prhDao.deletePropertyrightholder(1);
		if (result2==1){
			System.out.println("propertyrightholder deleted!!");
		}else{ 
			System.out.println("there is no propertyrightholder for this id");
		}
		*/
		
	}
}
