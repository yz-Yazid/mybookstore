package com.yz.javaweb.web;


/**
 * 该类是一个带条件查询书的信息，封装类。
 * 这个类的查询条件较为简单。只有按照价格和页数来封装的。
 * @author Administrator
 *
 */
public class CriteriaBook {

	private float minPrice = 0;
	private float maxPrice = Integer.MAX_VALUE;

	private int pageNo;

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public CriteriaBook(float minPrice, float maxPrice, int pageNo) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.pageNo = pageNo;
	}

}
