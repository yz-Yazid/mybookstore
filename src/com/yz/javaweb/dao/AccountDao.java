package com.yz.javaweb.dao;

import com.yz.javaweb.domain.Account;

public interface AccountDao {

	/**
	 * 根据 accountId 来获取到一个Account 的对象。
	 * 
	 * @param accountId
	 * @return
	 */
	public abstract Account get(Integer accountId);

	/**
	 * 根据传入的 accountId ，amount 更新指定账户的余额，扣除 指定的 amount 的钱数。
	 * 
	 * @param accountId
	 * @param amount
	 */
	public abstract void updateBalance(Integer accountId, float amount);

}
