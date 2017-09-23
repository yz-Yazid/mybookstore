package com.yz.javaweb.daoimpl;

import com.yz.javaweb.dao.UserDao;
import com.yz.javaweb.domain.User;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String userName) {
		String sql = "select * from user where userName = ?";
		return query(sql, userName);
	}

}
