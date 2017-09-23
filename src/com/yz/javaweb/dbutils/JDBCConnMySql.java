package com.yz.javaweb.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 获取数据连接和释放数据库连接的一个工具类。
 * 
 * @author Administrator
 *
 */
public class JDBCConnMySql {

	/**
	 * 获取数据库的连接。
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String jdbcUrl = "jdbc:mysql://localhost:3306/bookstore?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
			String userName = "root";
			String passWord = "";
			connection = DriverManager.getConnection(jdbcUrl, userName, passWord);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 释放数据库的连接。
	 * @param connection
	 */
	public static void relaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void relaseResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public static void relasePreparedStatement(PreparedStatement preparedStatement) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
