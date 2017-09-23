package com.yz.javaweb.domain;

public class Account {

	private Integer accountId;
	private Integer balance;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Account(Integer accountId, Integer balance) {
		super();
		this.accountId = accountId;
		this.balance = balance;
	}

	public Account() {

	}
}
