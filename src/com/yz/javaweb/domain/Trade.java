package com.yz.javaweb.domain;

import java.util.Date;

public class Trade {

	private Integer tradeId;

	private Date tradeTime;

	private Integer userId;

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Trade(Date tradeTime, Integer userId) {
		super();
		this.tradeTime = tradeTime;
		this.userId = userId;
	}

	public Trade() {

	}

}
