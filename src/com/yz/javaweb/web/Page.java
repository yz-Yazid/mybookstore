package com.yz.javaweb.web;

import java.util.List;

public class Page<T> {

	// 当前的页数.
	private int pageNo;

	// 当前页的List

	private List<T> list;

	// 每页显示多少天记录数。

	private int pageSize = 3;

	// 共有多少条记录数。

	private long totalItemNumber;

	// 对当前的页数进行初始化
	public Page(int pageNo) {
		this.pageNo = pageNo;
	}

	// 需要进行校验
	public int getPageNo() {
		if (pageNo < 0) {
			pageNo = 1;
		}
		if (pageNo > getTotalPageNumber()) {
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public int getPageSize() {
		return pageSize;
	}
	// 获取总的页数。

	public int getTotalPageNumber() {

		int totalPageNumber = (int) totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0) {
			totalPageNumber++;
		}

		return totalPageNumber;
	}

	// 设置总的记录数。
	public void setTotalItemNumber(long totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	// 是否有下一页，判断当前的pageNo 是否小于总的页数。
	public boolean isHasNext() {
		if (getPageNo() < getTotalPageNumber()) {
			return true;
		}
		return false;
	}

	// 判断是否有上一页，判断当前的pageNo 是否大于一页的记录数。
	public boolean isHasPrev() {

		if (getPageNo() > 1) {
			return true;
		}

		return false;
	}

	public int getPrevPage() {
		if (isHasPrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}

	public int getNextPage() {
		if (isHasNext()) {
			return getPageNo() + 1;
		}
		return getPageNo();
	}

}
