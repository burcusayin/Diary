package com.google.gwt.diary.server.model;


public class PropertyRightHolder {
	
	int holderId;
	String holderName;
	String holderSurname;
	String holderEmail;
	String holderPhone;
	
	
	
	public PropertyRightHolder(int holderId, String holderName,
			String holderSurname, String holderEmail, String holderPhone) {
		super();
		this.holderId = holderId;
		this.holderName = holderName;
		this.holderSurname = holderSurname;
		this.holderEmail = holderEmail;
		this.holderPhone = holderPhone;
	}
	
	public int getHolderId() {
		return holderId;
	}
	public void setHolderId(int holderId) {
		this.holderId = holderId;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getHolderSurname() {
		return holderSurname;
	}
	public void setHolderSurname(String holderSurname) {
		this.holderSurname = holderSurname;
	}
	public String getHolderEmail() {
		return holderEmail;
	}
	public void setHolderEmail(String holderEmail) {
		this.holderEmail = holderEmail;
	}
	public String getHolderPhone() {
		return holderPhone;
	}
	public void setHolderPhone(String holderPhone) {
		this.holderPhone = holderPhone;
	}
	
	


}
