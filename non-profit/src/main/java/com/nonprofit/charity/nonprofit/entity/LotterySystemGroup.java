package com.nonprofit.charity.nonprofit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LotterySystemGroup {
	
	@Id
	@GeneratedValue
	private Integer lId;
	private String lotteryGroupName;
	
	public LotterySystemGroup(Integer lId, String lotteryGroupName) {
		super();
		this.lId = lId;
		this.lotteryGroupName = lotteryGroupName;
	}
	public Integer getlId() {
		return lId;
	}
	public void setlId(Integer lId) {
		this.lId = lId;
	}
	public String getLotteryGroupName() {
		return lotteryGroupName;
	}
	public void setLotteryGroupName(String lotteryGroupName) {
		this.lotteryGroupName = lotteryGroupName;
	}

}
