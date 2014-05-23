package com.google.gwt.diary;

import java.util.ArrayList;

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
		
		result = impl.takeLogin("iyte", "ceng");
		
		if(result.get(0) == "OK"){
			System.out.println("it is a valid login");
		}
		else{
			fail("it is an invalid login");
		}
	}
	
	
	@Test
	public void testDiary(){
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		
		result = impl.takeDiary("Hello world");
		//result = impl.takeDiary("");
		
		if(result == "OK"){
			System.out.println("it is a valid diary");
		}
		else{
			fail("Diary area is empty");
		}
	}
	
	@Test
	public void testViewDiary(){
		
		ArrayList<ArrayList<String>> result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		
		result = impl.viewDiary();
		
		if(result.size() != 0){
			System.out.println("View is successful");
		}
		else{
			System.out.println("there is no diary to view!");
		}
	}
	
	@Test
	public void testShowDiaryContent(){
		
		String result;
		long id = 1;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		
		result = impl.showDiaryContent(id);
		
		if(result != null){
			System.out.println("Content is: " + result);
		}
		else{
			fail("there is no content to show!");
		}
	}
	
	@Test
	public void testTakeNewAccount(){
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		ArrayList<String> list = new ArrayList<String>();
		list.add("zyn");
		list.add("zyn03");
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
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		long id = 1;
		String input = "editedDiary";

		result = impl.editDiary(id,input);
		
		if(result.equalsIgnoreCase("OK")){
			System.out.println("Diary is edited!");
		}
		else{
			fail("Diary couldn't be edited!");
		}
	}
	
	@Test
	public void testDeleteDiary(){
		
		String result;
		GreetingServiceImpl impl = new GreetingServiceImpl();
		long id = 1;

		result = impl.deleteDiary(id);
		
		if(result.equalsIgnoreCase("OK")){
			System.out.println("Diary is deleted!");
		}
		else{
			fail("Diary couldn't be deleted!");
		}
	}
}