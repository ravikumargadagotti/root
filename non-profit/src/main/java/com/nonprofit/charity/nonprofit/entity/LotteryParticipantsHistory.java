package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotteryParticipantsHistory {
	
	@Id
	@GeneratedValue
	private Integer lId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String lotteryGroupName;
	private String lotteryPickDate;
	
	public LotteryParticipantsHistory(Integer lId, String firstName, String lastName, String email, String phone,
			String lotteryGroupName, String lotteryPickDate) {
		super();
		this.lId = lId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.lotteryGroupName = lotteryGroupName;
		this.lotteryPickDate = lotteryPickDate;
	}
	public Integer getlId() {
		return lId;
	}
	public void setlId(Integer lId) {
		this.lId = lId;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLotteryGroupName() {
		return lotteryGroupName;
	}
	public void setLotteryGroupName(String lotteryGroupName) {
		this.lotteryGroupName = lotteryGroupName;
	}
	public String getLotteryPickDate() {
		return lotteryPickDate;
	}
	public void setLotteryPickDate(String lotteryPickDate) {
		this.lotteryPickDate = lotteryPickDate;
	}

}
