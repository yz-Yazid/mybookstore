package com.yz.javaweb.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yz.javaweb.dbutils.JDBCConnMySql;
import com.yz.javaweb.web.ConnectionContext;

/**
 * Servlet Filter implementation class TranactionFilter
 */
@WebFilter("/*")
public class TranactionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public TranactionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection connection = null;

		try {
			// 1.获取连接。
			connection = JDBCConnMySql.getConnection();

			// 2.开启事务。
			connection.setAutoCommit(false);

			// 3.利用ThreadLocal，绑定连接。
			ConnectionContext.getInstance().bind(connection);

			// 4.提交给Servlet。
			chain.doFilter(request, response);

			// 5.提交事务。
			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				// 回滚事务
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + "/error.jsp");
		} finally {

			// 6.取消绑定的连接。
			ConnectionContext.getInstance().remove();

			// 7.取消连接。
			JDBCConnMySql.relaseConnection(connection);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
