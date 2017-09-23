package com.yz.javaweb.dao;

import com.yz.javaweb.domain.User;

public interface UserDao {

	/**
	 * 根据用户名来获取到 User 对象。
	 * @param userName
	 * @return
	 */
	public abstract User getUser(String userName);
	
}
