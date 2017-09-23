package com.yz.javaweb.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;

public class ShopingCart {
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	/**
	 * 更新购物车中某一本的数量。
	 * 
	 * @param id
	 * @param quantity
	 */
	public void updateItemQuantity(Integer id, int quantity) {
		ShoppingCartItem sCartItem = books.get(id);
		if (sCartItem != null) {
			sCartItem.setQuantity(quantity);
		}

	}

	/**
	 * 移除一个购物车Item，即ShoppingCartItem
	 * 
	 * @param id
	 */
	public void removeItem(Integer id) {
		books.remove(id);
	}

	/**
	 * 清空购物车。
	 */
	public void clear() {
		books.clear();
	}

	/**
	 * 判断购物车是否为空。
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return books.isEmpty();
	}

	/**
	 * 获取购物车中所有的书的总价。
	 * 
	 * @return
	 */
	public float getTotalMoney() {
		float total = 0;

		for (ShoppingCartItem sCartItem : getItems()) {
			total += sCartItem.getItemMoney();
		}

		return total;
	}

	/**
	 * 获取所有的book的总量。
	 * 
	 * @return
	 */
	public int getBookNumber() {
		int total = 0;
		for (ShoppingCartItem sCartItem : getItems()) {
			total += sCartItem.getQuantity();
		}

		return total;
	}

	/**
	 * 获取购物车中的每一个ShoppingCartItem。
	 * 
	 * @return
	 */
	public Collection<ShoppingCartItem> getItems() {
		return books.values();
	}

	/**
	 * 获取所有的图书信息。
	 * 
	 * @return
	 */
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}

	/**
	 * 判断是否有该bookid的图书。
	 * 
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id) {
		return books.containsKey(id);
	}

	/**
	 * 向map集合中添加图书。
	 */
	public void addBook(Book book) {
		// 1.判断该集合中是否含有该图书。
		ShoppingCartItem sCartItem = books.get(book.getBookId());
		if (sCartItem == null) {
			sCartItem = new ShoppingCartItem(book);
			books.put(book.getBookId(), sCartItem);
		} else {
			sCartItem.increment();
		}
	}
}
