package com.yz.javaweb.daoimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yz.javaweb.dao.BookDao;
import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;
import com.yz.javaweb.web.CriteriaBook;
import com.yz.javaweb.web.Page;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public Book getBook(int id) {

		String sql = "select * from book where bookId = ?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {

		Page<Book> page = new Page<Book>(cb.getPageNo());
		page.setTotalItemNumber(getTotalBookNumber(cb));
		// 校验pageNo的合法性。
		cb.setPageNo(page.getPageNo());
		page.setList(getPageBook(cb, 3));

		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "select count(bookId) from book where price >= ? and price <= ?";

		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageBook(CriteriaBook cb, int pageSize) {

		String sql = "select * from book where price >= ? and price <= ?" + "limit ?,?";

		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), (cb.getPageNo() - 1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql = "select storeNumber from book where bookId = ?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
		String sql = "update book set salesAmount = salesAmount + ? , storeNumber = storeNumber - ?"
				+ " where bookId = ?";
		Object[][] params = null;
		params = new Object[items.size()][3];
		List<ShoppingCartItem> sList = new ArrayList<>(items);
		for (int i = 0; i < items.size(); i++) {
			params[i][0] = sList.get(i).getQuantity();
			params[i][1] = sList.get(i).getQuantity();
			params[i][2] = sList.get(i).getBook().getBookId();
		}
		batch(sql, params);
	}

}
