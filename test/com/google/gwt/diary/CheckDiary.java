package com.google.gwt.diary;

import java.util.ArrayList;

import com.google.gwt.diary.db.dao.DiaryDAO;
import com.google.gwt.diary.db.dao.DiaryDAOFactory;
import com.google.gwt.diary.server.GreetingServiceImpl;

import org.junit.Test;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;


public class CheckDiary {
	@BeforeClass
	public static void setUpBeforeTest() throws Exception{

	}
	
	
	@Test
	public void testName(){
		Boolean result = false;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		
		result = impl.isItValid("iyte");
		//result = impl.isItValid("");
		
		if(result == true){
			System.out.println("it is a valid name");
		}
		else{
			fail("it is an invalid name");
		}
	}

	
	@Test
	public void testLogin(){
		
		ArrayList<String> result = new ArrayList<>();
		GreetingServiceImpl impl = new GreetingServiceImpl();
		
		result = impl.takeLogin("bc", "bc10418560477");
		
		if(result.get(0) == "OK"){
			System.out.println("it is a valid login");
		}
		else{
			fail("it is an invalid login");
		}
	}
	
	
	@Test
	public void testDiary(){
		ArrayList<String> result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		result = impl.takeLogin("bc", "bc10418560477");
		if(result.get(0).equalsIgnoreCase("OK"))
		{
			if(!result.get(1).equalsIgnoreCase("Wrong username or password!"))
			{
				String r = impl.takeDiary("testDiary!");
				if(r == "OK"){
					System.out.println("it is a valid diary");
				}
				else{
					fail("Diary area is empty");
				}
			}
		}	
	}
	
	@Test
	public void testViewDiary(){
		
		ArrayList<String> result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		result = impl.takeLogin("bc", "bc10418560477");
		if(result.get(0).equalsIgnoreCase("OK"))
		{
			if(!result.get(1).equalsIgnoreCase("Wrong username or password!"))
			{
				String r = impl.takeDiary("testDiaryView01!");
				if(r.equalsIgnoreCase("OK"))
				{
					ArrayList<ArrayList<String>> b = impl.viewDiary();
					
					if(b.size() != 0){
						System.out.println("View is successful");
					}
					else{
						fail("there is no diary to view!");
					}
				}
			}
		}	
	}
	
	@Test
	public void testShowDiaryContent(){
		
		ArrayList<String> result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
		result = impl.takeLogin("bc", "bc10418560477");
		if(result.get(0).equalsIgnoreCase("OK"))
		{
			if(!result.get(1).equalsIgnoreCase("Wrong username or password!"))
			{
				String r = impl.takeDiary("testShowDiary01!");
				if(r.equalsIgnoreCase("OK"))
				{
					ArrayList<ArrayList<String>> b = impl.viewDiary();
					
					if(b.size() != 0){
						long id = dDao.getMaxDiaryId();
						String resul = impl.showDiaryContent(id);
						
						if(resul != null){
							System.out.println("Content is: " + resul);
						}
						else{
							fail("there is no content to show!");
						}
					}
					else{
						fail("there is no diary to view!");
					}
				}
			}
		}	
	}
	
	@Test
	public void testTakeNewAccount(){
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		ArrayList<String> list = new ArrayList<String>();
		list.add("zynTest01");
		list.add("passTest02");
		list.add("sym");
		list.add("zeynep");
		list.add("54122");
		list.add("zyn@gmail.com");
		list.add("sfdfddf");
		result = impl.takeNewAccount(list);
		
		if(result.equalsIgnoreCase("OK")){
			System.out.println("New account is added!");
		}
		else{
			fail("New account couldn't be added!");
		}
	}
	
	@Test
	public void testTakeNewHolder(){
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		ArrayList<String> list = new ArrayList<String>();
		list.add("ali");
		list.add("sadi");
		list.add("ali@gmail.com");
		list.add("54524147");
		list.add("View");
		result = impl.takeNewHolder(list);
		
		if(result.equalsIgnoreCase("OK")){
			System.out.println("New holder is added!");
		}
		else{
			fail("New holder couldn't be added!");
		}
	}
	
	@Test
	public void testEditDiary(){
		
		ArrayList<String> result;
		DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
		GreetingServiceImpl impl = new GreetingServiceImpl();
		result = impl.takeLogin("bc", "bc10418560477");
		if(result.get(0).equalsIgnoreCase("OK"))
		{
			if(!result.get(1).equalsIgnoreCase("Wrong username or password!"))
			{
				String r = impl.takeDiary("testDiary01!");
				if(r.equalsIgnoreCase("OK"))
				{
					long id = dDao.getMaxDiaryId();
					String b = impl.editDiary(id, "testDiary01Edited");
					
					if(b.equalsIgnoreCase("OK")){
						System.out.println("Diary is edited!");
					}
					else{
						fail("Diary couldn't be edited!");
					}
				}
			}
		}	
	}
	
	@Test
	public void testDeleteDiary(){
		
		ArrayList<String> result;
		DiaryDAO dDao = DiaryDAOFactory.getDiaryDAO();
		GreetingServiceImpl impl = new GreetingServiceImpl();
		result = impl.takeLogin("bc", "bc10418560477");
		if(result.get(0).equalsIgnoreCase("OK"))
		{
			if(!result.get(1).equalsIgnoreCase("Wrong username or password!"))
			{
				String r = impl.takeDiary("testDiary!");
				if(r.equalsIgnoreCase("OK"))
				{
					long id = dDao.getMaxDiaryId();
					String b = impl.deleteDiary(id);
					
					if(b.equalsIgnoreCase("OK")){
						System.out.println("Diary is deleted!");
					}
					else{
						fail("Diary couldn't be deleted!");
					}
				}
			}
		}	
	}
}