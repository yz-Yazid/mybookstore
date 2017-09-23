package com.yz.javaweb.service;

import com.yz.javaweb.dao.UserDao;
import com.yz.javaweb.daoimpl.UserDaoImpl;
import com.yz.javaweb.domain.User;

public class UserService {

	private UserDao userDao = new UserDaoImpl();

	public User getUser(String userName) {
		return userDao.getUser(userName);
	}

}
