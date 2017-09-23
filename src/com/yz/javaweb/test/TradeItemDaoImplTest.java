package com.yz.javaweb.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import com.yz.javaweb.dao.TradeItemDao;
import com.yz.javaweb.daoimpl.TradeItemDaoImpl;
import com.yz.javaweb.domain.TradeItem;

public class TradeItemDaoImplTest {

	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();

	@Test
	public void testBatchSave() {
		Collection<TradeItem> items = new ArrayList<>();
		TradeItem tradeItem = new TradeItem();
		tradeItem.setBookId(1);
		tradeItem.setQuantity(5);
		tradeItem.setTradeId(2);
		items.add(tradeItem);

		tradeItem = new TradeItem();
		tradeItem.setBookId(2);
		tradeItem.setQuantity(3);
		tradeItem.setTradeId(2);
		items.add(tradeItem);

		tradeItem = new TradeItem();
		tradeItem.setBookId(3);
		tradeItem.setQuantity(1);
		tradeItem.setTradeId(2);
		items.add(tradeItem);

		tradeItemDao.batchSave(items);

	}

	@Test
	public void testGetTradeItemsWithTradeId() {

		Set<TradeItem> itemsWithTradeId = tradeItemDao.getTradeItemsWithTradeId(2);
		System.out.println(itemsWithTradeId);
	}

}
