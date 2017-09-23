package com.yz.javaweb.dao;

import java.util.List;

/**
 * 实际操作的泛型的Dao接口。
 * @author Administrator
 *
 * @param <T>
 */
public interface Dao<T> {

	/**
	 * 执行插入操作，插入成功后，返回插入的id.
	 * @param sql
	 * @param args
	 * @return
	 */
	long insert(String sql, Object... args);

	/**
	 * 执行更新操作。
	 * @param sql
	 * @param args
	 */
	void update(String sql, Object... args);

	/**
	 * 执行查询操作，返回查询后的一个泛型的类。
	 * @param sql
	 * @param args
	 * @return
	 */
	T query(String sql, Object... args);

	/**
	 * 进行查询操作，返回查询后的多条泛型类的一个集合。
	 * @param sql
	 * @param args
	 * @return
	 */
	List<T> queryForList(String sql, Object... args);

	/**
	 * 获取到某一个字段上的一个值。
	 * @param sql
	 * @param args
	 * @return
	 */
	<V> V getSingleVal(String sql, Object... args);

	/**
	 * 进行批处理的一个方法。
	 * 多条sql语句进行事务处理，批处理。
	 * @param sql
	 * @param args
	 */
	void batch(String sql, Object[]... args);

}
