package com.yz.javaweb.test;

import java.sql.Connection;

import org.junit.Test;

import com.yz.javaweb.dbutils.JDBCConnMySql;

public class JDBCConnMySqlTest {

	@Test
	public void testGetConnection() {
		Connection connection = JDBCConnMySql.getConnection();
		System.out.println(connection);
	}

}
