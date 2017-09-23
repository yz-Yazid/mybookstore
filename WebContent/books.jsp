<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		//跳转的 js 实现。
		$("#pageNo")
				.change(
						function() {
							//获取到文本框中用户输入的跳转信息。
							var val = $(this).val();
							//去掉用户开头输入的空格。
							val = $.trim(val);
							var flag = false;
							var pageNo = 0;
							//1.校验val值是否为数字，而不是字符等不合法的值。
							//正则表达式，判断输入的是否为数字。
							var reg = /^\d+$/g;

							if (reg.test(val)) {
								//2。判断该值是否在合法的范围之内。
								pageNo = parseInt(val);
								if (pageNo >= 1
										|| pageNo <= parseInt("${bookpage.totalPageNumber}")) {
									flag = true;
								}
							}
							//文本框中输入的不合法，清空文本框中的内容。
							if (!flag) {
								alert("输入的不是合法的页码！！！");
								$(this).val("");
								return;
							}
							//3.页面的跳转。
							//加上查询条件的跳转。
							var href = "getBooksServlet?method=getBooks&pageNo="
									+ pageNo + "&" + $(":hidden").serialize();
							window.location.href = href;
						});
	})
</script>
<%@include file="/jsutils/jQueryFound.jsp"%>
</head>
<body>

	<center>

		<c:if test="${param.title != null}">
			您已经将 ${param.title } 放入到购物车中。
			<br>
			<br>
		</c:if>
		<c:if test="${!empty sessionScope.ShopingCart.books }">
			您的购物车中有 ${sessionScope.ShopingCart.bookNumber } 本书，<a
				href="getBooksServlet?method=forwordPage&page=shopcart&pageNo=${bookpage.pageNo }">查看购物车</a>
		</c:if>
		<br>
		<br>

		<form action="getBooksServlet?method=getBooks" method="post">
			Price: <input type="text" size="1" name="minPrice" /> - <input
				type="text" size="1" name="maxPrice" /> <input type="submit"
				value="submit" />
		</form>

		<br>
		<br>
		<table>

			<c:forEach items="${bookpage.list }" var="book">
				<tr>
					<td><a
						href="getBooksServlet?method=getBook&pageNo=${bookpage.pageNo }&bookId=${book.bookId}">${book.title }</a>
						<br> <font size="1">${book.author }</font></td>
					<td>${book.price }</td>
					<td><a
						href="getBooksServlet?method=addToCart&&pageNo=${bookpage.pageNo }&bookId=${book.bookId}&title=${book.title}">加入购物车</a></td>
				</tr>
			</c:forEach>

		</table>

		<br>
		<br> 共${bookpage.totalPageNumber }页 &nbsp;&nbsp;
		当前第${bookpage.pageNo }页 &nbsp;&nbsp;
		<c:if test="${bookpage.hasPrev }">
			<a href="getBooksServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a
				href="getBooksServlet?method=getBooks&pageNo=${bookpage.prevPage }">上一页</a>
		</c:if>

		<c:if test="${bookpage.hasNext }">
			<a
				href="getBooksServlet?method=getBooks&pageNo=${bookpage.totalPageNumber }">末页</a>
			&nbsp;&nbsp;
			<a
				href="getBooksServlet?method=getBooks&pageNo=${bookpage.nextPage }">下一页</a>
		</c:if>
		&nbsp;&nbsp; 转到<input type="text" size="1" id="pageNo" />页
	</center>

</body>
</html>