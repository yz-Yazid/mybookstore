package com.yz.javaweb.test;

import org.junit.Test;

import com.yz.javaweb.daoimpl.BookDaoImpl;

public class BaseDaoTest {

	// private BaseDao baseDao = new BaseDao();

	private BookDaoImpl bookDaoImpl = new BookDaoImpl();

	@Test
	public void testInsert() {
		// String sql = "insert into trade(tradeTime,userId) values(?,?)";
		// long id = baseDao.insert(sql, new Date(new
		// java.util.Date().getTime()), 1);
		// System.out.println(id);
	}

	@Test
	public void testUpdate() {
		// String sql = "update book set price = 88 where bookId = ?";
		// bookDaoImpl.update(sql, 1);
	}

	@Test
	public void testQuery() {
		// String sql = "select * from book where bookId = ?";
		// Book book = bookDaoImpl.query(sql, 1);
		// System.out.println(book.getAuthor() + book.getTitle());
	}

	@Test
	public void testQueryForList() {
		// String sql = "select * from book";
		// List<Book> books = bookDaoImpl.queryForList(sql);
		// for (Book book : books) {
		// System.out.println(book.getAuthor() + book.getTitle());
		// }
	}

	@Test
	public void testGetSingleVal() {
		// String sql = "select publishingDate from book where bookId = ?";
		// Date date = bookDaoImpl.getSingleVal(sql, 1);
		// System.out.println(date);
	}

	@Test
	public void testBatch() {
		String sql = "update book set salesAmount = ? , storeNumber = ? where bookId = ?";
		bookDaoImpl.batch(sql, new Object[] { 1, 1, 1 }, new Object[] { 2, 2, 2 }, new Object[] { 3, 3, 3 });
	}

}
