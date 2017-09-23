package com.yz.javaweb.test;

import org.junit.Test;

import com.yz.javaweb.dao.AccountDao;
import com.yz.javaweb.daoimpl.AccountDaoImpl;
import com.yz.javaweb.domain.Account;

public class AccountDaoImplTest {

	AccountDao accountDao = new AccountDaoImpl();

	@Test
	public void testGet() {
		Account account = accountDao.get(1);
		System.out.println(account.getBalance());
	}

	@Test
	public void testUpdateBalance() {
		accountDao.updateBalance(1, 69);
	}

}
