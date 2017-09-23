package com.yz.javaweb.test;

import org.junit.Test;

import com.yz.javaweb.dao.UserDao;
import com.yz.javaweb.daoimpl.UserDaoImpl;
import com.yz.javaweb.domain.User;

public class UserDaoImplTest {

	UserDao userDao = new UserDaoImpl();

	@Test
	public void testGetUser() {
		User user = userDao.getUser("yazid");
		System.out.println(user.getUserId() + "--" + user.getUserName() + "--" + user.getAccountId());
	}

}
