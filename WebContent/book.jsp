<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/jquery-1.7.2.js"></script>
<%@include file="/jsutils/jQueryFound.jsp" %>
</head>
<body>
	
	
	
	<center>
		<br><br>
		<font color="red" size="5">书的详情信息</font>
		<table cellpadding="10" cellspacing="1">
			<tr>
				<td>书名</td>
				<td>${book.title }</td>
			</tr>
			<tr>
				<td>价格</td>
				<td>${book.price }</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>${book.author }</td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td>${book.publishingDate }</td>
			</tr>
			<tr>
				<td>销量</td>
				<td>${book.salesAmount }</td>
			</tr>
			<tr>
				<td>库存量</td>
				<td>${book.storeNumber }</td>
			</tr>
		</table>
		<a href="getBooksServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
	</center>
	
</body>
</html>