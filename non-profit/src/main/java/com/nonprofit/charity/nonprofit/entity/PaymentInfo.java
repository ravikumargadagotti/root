package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentInfo {
	
	@Id
	@GeneratedValue
	private int paymentInfoId;
	private int cardNumber;
	private int securityCode;
	private String expiryDate;
	private String firstName;
	private String middleName;
	private String lastName;
	private String timeStamp;
	private String userName;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String suffix;
	
	public PaymentInfo() {
		
	}
	
	public PaymentInfo(int paymentInfoId, int cardNumber, int securityCode, String expiryDate, String firstName,
			String middleName, String timeStamp, String userName, String lastName, String address, String city,
			String state, String zipcode, String suffix) {
		super();
		this.paymentInfoId = paymentInfoId;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.expiryDate = expiryDate;
		this.firstName = firstName;
		this.middleName = middleName;
		this.timeStamp = timeStamp;
		this.userName = userName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.suffix = suffix;
	}

	public int getPaymentInfoId() {
		return paymentInfoId;
	}

	public void setPaymentInfoId(int paymentInfoId) {
		this.paymentInfoId = paymentInfoId;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryCode(String expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	

}
