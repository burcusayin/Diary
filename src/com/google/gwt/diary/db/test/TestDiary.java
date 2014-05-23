package com.google.gwt.diary.db.test;

import java.sql.Timestamp;

import model.Diary;

import com.google.gwt.diary.db.dao.DiaryDAO;
import com.google.gwt.diary.db.dao.DiaryDAOFactory;
import com.google.gwt.diary.db.dao.PersonDAO;
import com.google.gwt.diary.db.dao.PersonDAOFactory;
import com.ibm.icu.util.Calendar;

public class TestDiary {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		
		DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
		
		
		///// insertion
		Diary newDiary = new Diary();
		PersonDAO pDao = PersonDAOFactory.getPersonDAO();
			
		newDiary.setDiaryId(1);
		newDiary.setTitle("diary1");
		newDiary.setContent("Hello1");

		//date
		newDiary.setDiaryDate(Calendar.getInstance().getTime()); 
		 
		// time
        java.util.Date date= new java.util.Date();
        newDiary.setDiaryTime(new Timestamp(date.getTime()));
        
		newDiary.setPerson(pDao.getPersonByUsername("ayse"));
		
		int result = dDao.insertDiary(newDiary);
		if (result==1){
			System.out.println("diary added!!");
		}else{ 
			System.out.println("Can not be added");
		}
		
		
		
		/*
	    ///// selection
		Diary diary = null;
		diary = dDao.getDiaryById(1);
		
		if(diary != null){
			System.out.println("diary belongs to : " + diary.getPerson().getName());
			System.out.println("diary id : " + diary.getDiaryId());
			System.out.println("diary content : " + diary.getContent());
			System.out.println("diary date : " + diary.getDiaryDate());
			System.out.println("diary time : " + diary.getDiaryTime());
		}else{
			System.out.println("there is no diary for this id");
		}
		*/
		
		
		
		///// deletion
		int result2 = dDao.deleteDiary(18);
		if (result2==1){
			System.out.println("diary deleted!!");
		}else{ 
			System.out.println("there is no diary for this id");
		}
		
		
		
	}
}

