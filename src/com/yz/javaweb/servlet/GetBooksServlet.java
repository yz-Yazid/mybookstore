package com.yz.javaweb.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yz.javaweb.dbutils.BookStoreWebUtils;
import com.yz.javaweb.domain.Account;
import com.yz.javaweb.domain.Book;
import com.yz.javaweb.domain.ShoppingCartItem;
import com.yz.javaweb.domain.User;
import com.yz.javaweb.service.AccountService;
import com.yz.javaweb.service.BookService;
import com.yz.javaweb.service.UserService;
import com.yz.javaweb.web.CriteriaBook;
import com.yz.javaweb.web.Page;
import com.yz.javaweb.web.ShopingCart;

public class GetBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取到调用该GetBooksServlet的哪一个方法名。
		String methodName = request.getParameter("method");
		try {
			// 通过反射，根据方法名调用该方法。
			Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private BookService bookService = new BookService();

	public void getBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int pageNo = 1; // 设置页面的初始值为1.
		int minPrice = 0; // 默认最低价格为0元.
		int maxPrice = Integer.MAX_VALUE; // 默认最大价格为无穷大.

		String pageNoStr = request.getParameter("pageNo"); // 获取到页面传过来pageNo的值。字符串类型。
		String minPriceStr = request.getParameter("minPrice");// 获取到页面传过来minPrice的值。字符串类型。
		String maxPriceStr = request.getParameter("maxPrice");// 获取到页面传过来maxPrice的值。字符串类型。

		try {
			pageNo = Integer.parseInt(pageNoStr); // 字符串转化为整型类型。
		} catch (NumberFormatException e) {
		}
		try {
			minPrice = Integer.parseInt(minPriceStr); // 字符串转化为整型类型。
		} catch (NumberFormatException e) {
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr); // 字符串转化为整型类型。
		} catch (NumberFormatException e) {
		}
		// 构造CriteriaBook对象。拿pageNo和minPrice和maxPrice三个值构造出CriteriaBook对象。用于带条件的查询。封装为一个对象了。
		CriteriaBook cb = new CriteriaBook(minPrice, maxPrice, pageNo);
		// 根据封装好的查询条件，获取到这一页对象。bookService是一个中间跳转的一个类，根据CriteriaBook获取到该页。
		Page<Book> page = bookService.getPage(cb);
		// 获取到该页的page就获取到了，该页的pageNo和list<book>
		// 把获取到的page存放到setAttribute中。
		request.setAttribute("bookpage", page);
		// 转发到books.jsp
		request.getRequestDispatcher("/books.jsp").forward(request, response);
	}

	public void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取到用户点击的bookid。
		String idStr = request.getParameter("bookId");
		// 初始化bookId，设置一个默认的值。-1，表示不存在。
		int bookId = -1;
		Book book = null;
		try {
			bookId = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		if (bookId > 0) {
			// 从数据库中获取到该书。bookService与底层dao交互，从数据库中拿到book。
			book = bookService.getBook(bookId);
		}
		// 判断book是否为空。
		if (book == null) {
			// 若book为空，跳转到error.jsp错误页面。结束。
			response.sendRedirect(request.getContextPath() + "/error.jsp");
			return;
		}
		// 不为空，存储到request中，转发，页面跳转。
		request.setAttribute("book", book);
		request.getRequestDispatcher("/book.jsp").forward(request, response);
	}

	public void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取到book的id。
		String idString = request.getParameter("bookId");

		int id = -1;
		boolean flag = false;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
		}

		if (id > 0) {
			// 2.获取到购物车对象。
			ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
			// 3.调用bookservice的addToCart方法把商品放入到购物车中。
			flag = bookService.addToCart(id, shopingCart);
		}

		if (flag) {
			// 4.直接调用getBooks方法。
			getBooks(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/error.jsp");
		}

	}

	public void forwordPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/" + page + ".jsp").forward(request, response);
	}

	private UserService userService = new UserService();

	public void cashBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String accountId = request.getParameter("accountId");
		StringBuffer error = voilteByFiled(userName, accountId);
		// 判断简单验证，即表单是否为空的验证。
		if (error.toString().equals("")) {
			// 判断用户名和账号是否匹配。
			error = voilteUser(userName, accountId);
			if (error.toString().equals("")) {
				// 判断库存是否充足。
				error = voilteBookStoreNumber(request);
				if (error.toString().equals("")) {
					// 判断余额是否充足。
					error = voilteBalance(request, accountId);
				}
			}
		}
		if (!error.toString().equals("")) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/cash.jsp").forward(request, response);
			return;
		}
		// 所有的验证完成之后，开始结账操作。
		bookService.cash(BookStoreWebUtils.getShopingCart(request), userName, accountId);
		response.sendRedirect(request.getContextPath() + "/success.jsp");
	}

	/**
	 * 判断余额是否充足。
	 * 
	 * @param accountId
	 * @return
	 */

	private AccountService accountService = new AccountService();

	public StringBuffer voilteBalance(HttpServletRequest request, String accountId) {
		StringBuffer error = new StringBuffer("");
		Account account = accountService.getAccount(Integer.parseInt(accountId));
		Integer balance = account.getBalance();
		ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
		float totalMoney = shopingCart.getTotalMoney();
		if (totalMoney > balance) {
			error.append("账户余额不足!");
		}
		return error;
	}

	/**
	 * 判断库存是否充足。
	 * 
	 * @param request
	 * @return
	 */
	public StringBuffer voilteBookStoreNumber(HttpServletRequest request) {
		StringBuffer error = new StringBuffer("");
		ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
		// 获取到购物车中所有的item。即所有的BookId对应的ShoppingCartItem bookId : bookQuantity.
		Map<Integer, ShoppingCartItem> books = shopingCart.getBooks();
		Set<Integer> bookIds = books.keySet();
		for (Integer bookId : bookIds) {
			Book book = bookService.getBook(bookId);
			// 获取到商店所剩余该书的BookId。
			int storeNumber = book.getStoreNumber();
			// 获取到用户买的该书的数量。
			int getNumber = books.get(bookId).getQuantity();
			if (storeNumber < getNumber) {
				error.append(book.getTitle() + "库存不足!<br>");
			}
		}
		return error;
	}

	/**
	 * 判断用户名和账号是否匹配。
	 * 
	 * @param userName
	 * @param accountId
	 * @return
	 */
	public StringBuffer voilteUser(String userName, String accountId) {
		User user = userService.getUser(userName);
		boolean flag = false;
		if (user != null) {
			Integer accountId2 = user.getAccountId();
			if (accountId != null && accountId.trim().equals("" + accountId2)) {
				flag = true;
			}
		}
		StringBuffer error2 = new StringBuffer("");
		if (!flag) {
			error2.append("用户名和账号不匹配!");
		}
		return error2;
	}

	/**
	 * 判断表单最基本的表单域 ，是否为空。
	 * 
	 * @param userName
	 * @param accountId
	 * @return
	 */
	public StringBuffer voilteByFiled(String userName, String accountId) {
		StringBuffer error = new StringBuffer("");
		if (userName == null || userName.trim().equals("")) {
			error.append("用户名为空!<br>");
		}
		if (accountId == null || accountId.trim().equals("")) {
			error.append("账号为空!");
		}
		return error;
	}

	public void removeBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("bookId");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
		}
		ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
		bookService.removeBookFromShopCart(shopingCart, id);
		if (bookService.isEmpty(shopingCart)) {
			request.getRequestDispatcher("/emptycart.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/shopcart.jsp").forward(request, response);
	}

	public void clearBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
		bookService.clearBookFromShopCart(shopingCart);
		request.getRequestDispatcher("/emptycart.jsp").forward(request, response);
	}

	public void updateItemsQuantity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		ShopingCart shopingCart = BookStoreWebUtils.getShopingCart(request);
		int id = -1;
		int quantity = -1;
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
		} catch (NumberFormatException e) {
		}
		if (id > 0 && quantity > 0) {
			bookService.updateItemsQuantity(shopingCart, id, quantity);
		}

		// 传回JSON数据。
		Map<String, Object> result = new HashMap<>();
		result.put("bookNumber", shopingCart.getBookNumber());
		result.put("totalMoney", shopingCart.getTotalMoney());

		Gson gson = new Gson();
		String reStr = gson.toJson(result);

		response.setContentType("text/javascript");
		response.getWriter().print(reStr);

	}

}
