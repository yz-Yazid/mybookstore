package com.yz.javaweb.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import com.yz.javaweb.dao.AccountDao;
import com.yz.javaweb.dao.BookDao;
import com.yz.javaweb.dao.TradeDao;
import com.yz.javaweb.dao.TradeItemDao;
import com.yz.javaweb.dao.UserDao;
import com.yz.javaweb.daoimpl.AccountDaoImpl;
import com.yz.javaweb.daoimpl.BookDaoImpl;
import com.yz.javaweb.daoimpl.TradeDaoImpl;
import com.yz.javaweb.daoimpl.TradeItemDaoImpl;
import com.yz.javaweb.daoimpl.UserDaoImpl;
import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;
import com.yz.javaweb.domain.Trade;
import com.yz.javaweb.domain.TradeItem;
import com.yz.javaweb.web.CriteriaBook;
import com.yz.javaweb.web.Page;
import com.yz.javaweb.web.ShopingCart;

/**
 * 这是一个直接调用dao层的中间类。 可以在这个类中做很多不能再dao层做的事情。例如:业务代码
 * 
 * @author Administrator
 *
 */
public class BookService {

	private BookDao bookDao = new BookDaoImpl();
	private AccountDao accountDao = new AccountDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private TradeDao tradeDao = new TradeDaoImpl();
	private TradeItemDao tradeItemDao = new TradeItemDaoImpl();

	public Page<Book> getPage(CriteriaBook cBook) {
		return bookDao.getPage(cBook);
	}

	public Book getBook(int bookId) {

		return bookDao.getBook(bookId);
	}

	public boolean addToCart(int id, ShopingCart sCart) {
		Book book = bookDao.getBook(id);
		if (book != null) {
			sCart.addBook(book);
			return true;
		} else {
			return false;
		}
	}

	public void removeBookFromShopCart(ShopingCart shopingCart, int id) {
		shopingCart.removeItem(id);
	}

	public void clearBookFromShopCart(ShopingCart shopingCart) {
		shopingCart.clear();
	}

	public boolean isEmpty(ShopingCart shopingCart) {
		if (shopingCart.isEmpty()) {
			return true;
		}
		return false;
	}

	public void updateItemsQuantity(ShopingCart shopingCart, int id, int quantity) {
		shopingCart.updateItemQuantity(id, quantity);
	}

	public void cash(ShopingCart shopingCart, String userName, String accountId) {

		// 1.更新book 数据表中的saleAmount 和 storeNumber。
		bookDao.batchUpdateStoreNumberAndSalesAmount(shopingCart.getItems());

		// 2.更新account 账户中的余额 balance。
		accountDao.updateBalance(Integer.parseInt(accountId), shopingCart.getTotalMoney());

		// 3.向 trade 数据表中插入一条记录。
		Trade trade = new Trade();
		trade.setTradeTime(new Date(new java.util.Date().getTime()));
		trade.setUserId(userDao.getUser(userName).getUserId());
		tradeDao.insert(trade);

		// 4.向 tradeItem 数据表中插入若干条记录。
		Collection<TradeItem> items = new ArrayList<>();
		for (ShoppingCartItem sCartItem : shopingCart.getItems()) {
			TradeItem tradeItem = new TradeItem();

			tradeItem.setBookId(sCartItem.getBook().getBookId());
			tradeItem.setQuantity(sCartItem.getQuantity());
			tradeItem.setTradeId(trade.getTradeId());

			items.add(tradeItem);
		}
		tradeItemDao.batchSave(items);

		// 5.清空购物车。
		shopingCart.clear();

	}

}
