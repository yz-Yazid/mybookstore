package com.yz.javaweb.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yz.javaweb.dao.TradeItemDao;
import com.yz.javaweb.domain.TradeItem;

public class TradeItemDaoImpl extends BaseDao<TradeItem> implements TradeItemDao {

	@Override
	public void batchSave(Collection<TradeItem> items) {
		String sql = "insert into tradeitem (quantity,bookId,tradeId) values(?,?,?)";

		Object[][] params = new Object[items.size()][3];
		List<TradeItem> tradeItems = new ArrayList<>(items);
		for (int i = 0; i < tradeItems.size(); i++) {
			params[i][0] = tradeItems.get(i).getQuantity();
			params[i][1] = tradeItems.get(i).getBookId();
			params[i][2] = tradeItems.get(i).getTradeId();
		}
		batch(sql, params);
	}

	@Override
	public Set<TradeItem> getTradeItemsWithTradeId(Integer tradeId) {
		String sql = "select * from tradeitem where tradeId = ?";
		return new HashSet<>(queryForList(sql, tradeId));
	}

}
