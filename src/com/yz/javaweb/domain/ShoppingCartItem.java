package com.yz.javaweb.domain;

public class ShoppingCartItem {

	private Book book;

	private int quantity;

	public ShoppingCartItem(Book book) {
		this.book = book;
		this.quantity = 1;
	}

	public Book getBook() {
		return book;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getItemMoney() {
		return book.getPrice() * quantity;
	}

	public void increment() {
		quantity++;
	}

}
