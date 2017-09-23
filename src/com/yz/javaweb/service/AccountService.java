package com.yz.javaweb.service;

import com.yz.javaweb.dao.AccountDao;
import com.yz.javaweb.daoimpl.AccountDaoImpl;
import com.yz.javaweb.domain.Account;

public class AccountService {

	private AccountDao accountDao = new AccountDaoImpl();

	public Account getAccount(Integer accountId) {
		return accountDao.get(accountId);
	}

}
