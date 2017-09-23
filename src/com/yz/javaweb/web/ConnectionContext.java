package com.yz.javaweb.web;

import java.sql.Connection;

public class ConnectionContext {

	public ConnectionContext() {}

	public static ConnectionContext connectionContext = new ConnectionContext();

	public static ConnectionContext getInstance() {
		return connectionContext;
	}

	private ThreadLocal<Connection> cLocal = new ThreadLocal<>();

	public void bind(Connection connection) {
		cLocal.set(connection);
	}

	public Connection get() {
		return cLocal.get();
	}

	public void remove() {
		cLocal.remove();
	}

}
