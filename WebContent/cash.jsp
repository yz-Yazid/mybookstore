<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<br> <br> 您的购物车中有<font size="4" color="red">
			${sessionScope.ShopingCart.bookNumber } </font> 本书 <br> 总金额为:
		${sessionScope.ShopingCart.totalMoney } 元 <br> <br>

		<c:if test="${requestScope.error != null }">
			<font color="red">${requestScope.error }</font>
		</c:if>


		<form action="getBooksServlet?method=cashBooks" method="post">
			<table>
				<tr>
					<td>信用卡姓名:</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>信用卡账号:</td>
					<td><input type="text" name="accountId" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>