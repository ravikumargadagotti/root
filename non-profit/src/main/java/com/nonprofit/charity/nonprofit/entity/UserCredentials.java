package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserCredentials {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String userName;
	private String password;
	
	protected UserCredentials() {
		
	}
	
	public UserCredentials(Integer id, String userName, String password) {		
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public static UserCredentials findOne(Integer id) {
		return null;
	}

	
}
