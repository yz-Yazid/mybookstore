package com.yz.javaweb.dbutils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yz.javaweb.web.ShopingCart;

public class BookStoreWebUtils {
	/**
	 * 获取购物车对象。 从session获取，若没有该对象，则创建一个新的购物车对象，有的话就直接返回。
	 * 
	 * @param request
	 * @return
	 */
	public static ShopingCart getShopingCart(HttpServletRequest request) {
		HttpSession session = request.getSession();

		ShopingCart sCart = (ShopingCart) session.getAttribute("ShopingCart");
		if (sCart == null) {
			sCart = new ShopingCart();
			session.setAttribute("ShopingCart", sCart);
		}
		return sCart;
	}
}
