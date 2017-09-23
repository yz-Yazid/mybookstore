package com.yz.javaweb.domain;

public class User {

	private Integer userId;
	private String userName;

	private Integer accountId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public User(Integer userId, String userName, Integer accountId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.accountId = accountId;
	}

	public User() {

	}
}
