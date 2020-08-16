package com.SafeStore.UserManager;

import java.time.ZonedDateTime;

public class UserData {
	/**
	 * @author Sandesh MS
	 */
	
	private String firstName;
	private String lastName;
	//email: primary key
	private String email;
	private String password;
	boolean loginToken = false;
	ZonedDateTime lastLoginDateTime =  null;
	
	
	//THis constructor is used for Registration 
	public UserData(String firstName, String lastName, String email, String password, boolean loginToken,
			ZonedDateTime lastLoginDateTime) {
		//super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.loginToken = loginToken;
		this.lastLoginDateTime = lastLoginDateTime;
	}
	
	//This constructor is used for Login
	public UserData(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	


	public UserData(String firstName, String email, boolean loginToken, ZonedDateTime lastLoginDateTime) {
		super();
		this.firstName = firstName;
		this.email = email;
		this.loginToken = loginToken;
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getLoginToken() {
		return loginToken;
	}
	public void setLoginToken(boolean loginToken) {
		this.loginToken = loginToken;
	}
	public ZonedDateTime getLastLoginDateTime() {
		return lastLoginDateTime;
	}
	public void setLastLoginDateTime(ZonedDateTime lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserData && ((UserData) obj).getEmail().equalsIgnoreCase(this.getEmail()))
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "UserData [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	

	
	
}

