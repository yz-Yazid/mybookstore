package com.yz.javaweb.domain;

public class TradeItem {

	private Integer tradeItemId;

	private Integer quantity;

	private Integer bookId;

	private Integer tradeId;

	public Integer getTradeItemId() {
		return tradeItemId;
	}

	public void setTradeItemId(Integer tradeItemId) {
		this.tradeItemId = tradeItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public TradeItem(Integer tradeItemId, Integer quantity, Integer bookId, Integer tradeId) {
		super();
		this.tradeItemId = tradeItemId;
		this.quantity = quantity;
		this.bookId = bookId;
		this.tradeId = tradeId;
	}

	public TradeItem() {

	}

	@Override
	public String toString() {
		return "TradeItem [quantity=" + quantity + ", bookId=" + bookId + ", tradeId=" + tradeId + "]";
	}

}
