package com.google.gwt.diary.server.model;
 
public class PersonGroup extends Person{

	String groupName;
	String groupType;
	
	public PersonGroup(String userName, String password, String name,
			String surname, String email, String phoneNumber, String address, String groupName, String groupType) {
		super(userName, password, name, surname, email, phoneNumber, address);
		// TODO Auto-generated constructor stub
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
}
