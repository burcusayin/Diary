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
	
	
}