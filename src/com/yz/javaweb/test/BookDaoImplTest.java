package com.yz.javaweb.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.yz.javaweb.dao.BookDao;
import com.yz.javaweb.daoimpl.BookDaoImpl;
import com.yz.javaweb.dbutils.JDBCConnMySql;
import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;
import com.yz.javaweb.web.ConnectionContext;
import com.yz.javaweb.web.CriteriaBook;
import com.yz.javaweb.web.Page;

public class BookDaoImplTest {

	private BookDao bookDao = new BookDaoImpl();

	@Test
	public void testGetBook() {
		Connection connection = null;
		connection = JDBCConnMySql.getConnection();
		ConnectionContext.getInstance().bind(connection);
		Book book = bookDao.getBook(5);
		System.out.println(book.getAuthor() + "^^^^^" + book.getTitle());
	}

	@Test
	public void testGetPage() {
		CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 1);
		Page<Book> page = bookDao.getPage(cb);

		System.out.println("PageNo:" + page.getPageNo());
		System.out.println("TotalPageNumber:" + page.getTotalPageNumber());
		System.out.println("List:" + page.getList());
		System.out.println("PrevPage:" + page.getPrevPage());
		System.out.println("NextPage:" + page.getNextPage());
	}

	@Test
	public void testGetTotalBookNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageBook() {
		CriteriaBook cb = new CriteriaBook(0, Integer.MAX_VALUE, 2);
		List<Book> books = bookDao.getPageBook(cb, 3);
		System.out.println(books);
	}

	@Test
	public void testGetStoreNumber() {
		long storeNumber = bookDao.getStoreNumber(5);
		System.out.println(storeNumber);
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount() {
		Collection<ShoppingCartItem> list = new ArrayList<>();
		Book book = bookDao.getBook(1);
		ShoppingCartItem sItem = new ShoppingCartItem(book);
		sItem.setQuantity(11);
		list.add(sItem);

		book = bookDao.getBook(2);
		sItem = new ShoppingCartItem(book);
		sItem.setQuantity(12);
		list.add(sItem);

		book = bookDao.getBook(3);
		sItem = new ShoppingCartItem(book);
		sItem.setQuantity(13);
		list.add(sItem);

		book = bookDao.getBook(4);
		sItem = new ShoppingCartItem(book);
		sItem.setQuantity(14);
		list.add(sItem);

		bookDao.batchUpdateStoreNumberAndSalesAmount(list);

	}

}
