package com.google.gwt.diary.server.model;

public class Person {
	
	String userName;
	String password;
	String name;
	String surname;
	String email;
	String phoneNumber;
	String address;
	
	public Person(String userName, String password, String name, String surname, String email, String phoneNumber, String address)
	{
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public Person()
	{
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
