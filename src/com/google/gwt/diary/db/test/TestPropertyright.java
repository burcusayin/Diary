package com.google.gwt.diary.db.test;

import model.Propertyright;
import com.google.gwt.diary.db.dao.DiaryDAO;
import com.google.gwt.diary.db.dao.DiaryDAOFactory;
import com.google.gwt.diary.db.dao.PropertyrightDAO;
import com.google.gwt.diary.db.dao.PropertyrightDAOFactory;
import com.ibm.icu.util.Calendar;

public class TestPropertyright {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		PropertyrightDAO prDao = PropertyrightDAOFactory.getPropertyRightDAO();
		
		
		///// insertion
		Propertyright newPropertyright = new Propertyright();
		DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
			
		newPropertyright.setPropertyrightId(5);

		//date
		newPropertyright.setPropertyrightDate(Calendar.getInstance().getTime());
		 
		newPropertyright.setType("copy");
		newPropertyright.setDiary(dDao.getDiaryById(2));
		
		int result = prDao.insertPropertyright(newPropertyright);
		if (result==1){
			System.out.println("propertyright added!!");
		}else{ 
			System.out.println("Can not be added");
		}
		
		
		
		/*
	    ///// selection
		Propertyright propertyright = null;
		propertyright = prDao.getPropertyrightById(5);
		
		if(propertyright != null){
			System.out.println("propertyright id : " + propertyright.getPropertyrightId());
			System.out.println("propertyright date : " + propertyright.getPropertyrightDate());
			System.out.println("propertyright type : " + propertyright.getType());
			System.out.println("propertyright belongs to diary : " + propertyright.getDýary().getDiaryId());
		}else{
			System.out.println("there is no diary for this id");
		}
		*/
		
		
		/*
		///// deletion
		int result2 = prDao.deletePropertyright(5);
		if (result2==1){
			System.out.println("propertyright deleted!!");
		}else{ 
			System.out.println("there is no propertyright for this id");
		}
		*/
		
	}
}

