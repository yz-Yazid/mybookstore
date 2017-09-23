package com.yz.javaweb.dao;

import java.util.Set;

import com.yz.javaweb.domain.Trade;

public interface TradeDao {

	/**
	 * 根据参数，向数据库中插入一条交易记录。
	 * @param trade
	 */
	public abstract void insert(Trade trade);
	
	/**
	 * 根据传入的 userId 来获取到 和其相关联的 Set<Trade> 的集合。
	 * @param userId
	 * @return
	 */
	public abstract Set<Trade> getTradesWithUserId(Integer userId);
	
}
