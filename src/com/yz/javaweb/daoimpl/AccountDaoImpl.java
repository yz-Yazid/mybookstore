package com.yz.javaweb.daoimpl;

import com.yz.javaweb.dao.AccountDao;
import com.yz.javaweb.domain.Account;

public class AccountDaoImpl extends BaseDao<Account> implements AccountDao {

	@Override
	public Account get(Integer accountId) {

		String sql = "select * from account where accountId = ?";

		return query(sql, accountId);
	}

	@Override
	public void updateBalance(Integer accountId, float amount) {
		String sql = "update account set balance = balance - ? where accountId = ?";

		update(sql, amount, accountId);
	}

}
