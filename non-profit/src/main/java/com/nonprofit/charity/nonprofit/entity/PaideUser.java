package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class PaideUser {
	
	@javax.persistence.Id
	@GeneratedValue
	private Integer Id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String address;
	private String zipCode;
	private String phone;
	
	protected PaideUser() {
		
	}
	
	public PaideUser(String firstName, String lastName, String email, String address, String zipCode, String phone) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.address=address;
		this.zipCode=zipCode;
		this.phone=phone;
	}
	
	public PaideUser(Integer Id, String firstName, String middleName,String lastName, String email, String address, String zipCode, String phone) {
		this.Id=Id;
		this.firstName=firstName;
		this.middleName=middleName;
		this.lastName=lastName;
		this.email=email;
		this.address=address;
		this.zipCode=zipCode;
		this.phone=phone;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
