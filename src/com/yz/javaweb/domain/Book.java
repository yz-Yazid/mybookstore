package com.yz.javaweb.domain;

import java.util.Date;

public class Book {

	private Integer bookId;

	private String author;

	private String title;

	private float price;

	private Date publishingDate;

	private Integer salesAmount;

	private Integer storeNumber;

	public Book(Integer bookId, String author, String title, float price, Date publishingDate, Integer salesAmount,
			Integer storeNumber, String remark) {
		super();
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.price = price;
		this.publishingDate = publishingDate;
		this.salesAmount = salesAmount;
		this.storeNumber = storeNumber;
		this.remark = remark;
	}

	public Book() {

	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public Integer getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Integer getStoreNumber() {
		return storeNumber;
	}

	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String remark;

}
