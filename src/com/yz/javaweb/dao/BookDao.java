package com.yz.javaweb.dao;

import java.util.Collection;
import java.util.List;

import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;
import com.yz.javaweb.web.CriteriaBook;
import com.yz.javaweb.web.Page;

/**
 * 
 * @author Administrator
 *
 */
public interface BookDao {

	/**
	 * 根据指定的id返回book对象。
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id);
	/**
	 * 根据传入的 CriteriaBook 对象返回对应的Page 对象。
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数。
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回对应的 List<Book> 的集合。
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getPageBook(CriteriaBook cb, int pageSize);
	
	/**
	 * 返回指定的id 的book 的StoreNumber字段的值。
	 * @param id
	 * @return
	 */
	public abstract int getStoreNumber(Integer id);
	
	/**
	 * 根据传入的Collection<ShoppingCartItem> 的集合。
	 * 批量更新 book 数据表的 storenumber 和 salesnumber字段的值。
	 * @param items
	 */
	public abstract void batchUpdateStoreNumberAndSalesAmount(Collection<ShoppingCartItem> items);
}
