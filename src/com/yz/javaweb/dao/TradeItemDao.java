package com.yz.javaweb.dao;

import java.util.Collection;
import java.util.Set;

import com.yz.javaweb.domain.TradeItem;

public interface TradeItemDao {

	
	/**
	 * 批量保存 Collection<TradeItem> 的集合。
	 * @param items
	 */
	public abstract void batchSave(Collection<TradeItem> items);
	
	/**
	 * 根据 tradeId 获取到  TradeItems 的 Set 集合。
	 * @param tradeId
	 * @return
	 */
	public abstract Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId);
	
	
}
