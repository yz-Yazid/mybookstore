package com.yz.javaweb.daoimpl;

import java.util.HashSet;
import java.util.Set;

import com.yz.javaweb.dao.TradeDao;
import com.yz.javaweb.domain.Trade;

public class TradeDaoImpl extends BaseDao<Trade> implements TradeDao {

	@Override
	public void insert(Trade trade) {
		String sql = "insert into trade (tradeTime,userId) values(?,?)";
		long tradeId = insert(sql, trade.getTradeTime(), trade.getUserId());
		trade.setTradeId((int) tradeId);
	}

	@Override
	public Set<Trade> getTradesWithUserId(Integer userId) {
		String sql = "select * from trade where userId = ?";
		return new HashSet<>(queryForList(sql, userId));
	}

}
