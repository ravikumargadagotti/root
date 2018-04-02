package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotteryState {
	
	@Id
	@GeneratedValue
	private Integer lId;
	private String lotteryName;
	private String startDate;
	private String endDate;
	
	public LotteryState(Integer lId, String lotteryName, String startDate, String endDate) {
		super();
		this.lId = lId;
		this.lotteryName = lotteryName;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Integer getlId() {
		return lId;
	}
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	public String getLotteryName() {
		return lotteryName;
	}
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
