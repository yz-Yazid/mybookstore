package com.yz.javaweb.daoimpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yz.javaweb.dao.Dao;
import com.yz.javaweb.dbutils.JDBCConnMySql;
import com.yz.javaweb.web.ConnectionContext;

public class BaseDao<T> implements Dao<T> {

	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;

	public BaseDao() {
		Type superClass = getClass().getGenericSuperclass();
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;

			Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}

	@Override
	public long insert(String sql, Object... args) {
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionContext.getInstance().get();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					preparedStatement.setObject(i + 1, args[i]);
				}
			}
			preparedStatement.executeUpdate();
			// 获取产生的主键值.
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCConnMySql.relasePreparedStatement(preparedStatement);
			JDBCConnMySql.relaseResultSet(resultSet);
		}

		return id;
	}

	@Override
	public void update(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = ConnectionContext.getInstance().get();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public T query(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <V> V getSingleVal(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = ConnectionContext.getInstance().get();
			return (V) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... args) {
		Connection connection = null;

		try {
			connection = ConnectionContext.getInstance().get();
			queryRunner.batch(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
