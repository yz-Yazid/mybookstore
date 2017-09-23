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
		//获取到class属性为delete的节点。添加点击事件 。
		$(".delete").click(function() {
			//获取到该节点的前两个父节点。    拿到 tr 节点。
			var $tr = $(this).parent().parent();
			//获取到tr下面子节点的第一个td节点。获取到文本框中的内容。为书名。
			var title = $.trim($tr.find("td:first").text());
			//点击确认事件。确定删除为true ，否则为 false。
			var flag = confirm("确定要删除" + title + "的信息吗？");
			if (flag) {
				return true;
			}
			return false;
		});
		//ajax修改单个商品的数量。
		//1. 获取所有的text，为其注册onChange事件。confirm

		$(":text").change(function() {
			//获取到用户输入的文本框中 的内容。
			var quantityVal = $.trim(this.value);
			//正则表达式。判断输入的是否是合法的数字。
			var reg = /^\d+$/g;
			var flag = false;
			var quantity = -1;
			//如果，输入的是合法的数字，并且输入的不是小于0。把flag的值置为true。
			if (reg.test(quantityVal)) {
				quantity = parseInt(quantityVal);
				if (quantity >= 0)
					flag = true;
			}
			if (!flag) {
				alert("输入的数量不合法!");
				//把原来文本框中的内容从新显示出来。在input标签中加了一个class属性。
				$(this).val($(this).attr("class"));
				return;
			}
			//获取到该节点的前两个父节点。    拿到 tr 节点。
			var $tr = $(this).parent().parent();
			//获取到tr下面子节点的第一个td节点。获取到文本框中的内容。为书名。
			var title = $.trim($tr.find("td:first").text());

			if (quantity == 0) {
				var flag2 = confirm("确定要删除" + title + "吗?");
				if (flag2) {
					//获取到tr下面子节点的最后一个td节点。  a  标签
					var $a = $tr.find("td:last").find("a");
					//alert($a[0].onclick);
					//得到a节点的onclick属性的值，然后加个()就执行该函数了。function
					$a[0].onclick();
					return;
				}
			}

			var flag = confirm("确定要修改" + title + "的数量吗?");
			if (!flag) {
				$(this).val($(this).attr("class"));
				return;
			}
			//2. 请求地址getBooksServlet。
			var url = "getBooksServlet";
			//3. 请求参数method=updateItemsQuantity，id:xx  quantity:val time:new Date();
			var idVal = $.trim(this.name);

			var args = {
				"method" : "updateItemsQuantity",
				"id" : idVal,
				"quantity" : quantityVal,
				"time" : new Date()
			};
			//4.在updateItemsQuantity方法中获取到id，和quantity，和ShopCart购物车对象。调用service方法，更新购物车中的数据。

			//5. 返回JSON 数据: bookNumber，totalMoney
			$.post(url, args, function(data) {
				var booknumber = data.bookNumber;
				var totalmoney = data.totalMoney;
				$("#totalMoney").text("总金额为: " + totalmoney + " 元");
				$("#bookNumber").text("您的购物车中有" + booknumber + "本书")
			}, "JSON");

			//6. 更新页面上的数据。

		});

	})
</script>
<%@include file="/jsutils/jQueryFound.jsp"%>
</head>
<body>



	<center>
		<h2>购物车详情信息</h2>
		<div id="bookNumber">
			您的购物车中有<font size="4" color="red">
				${sessionScope.ShopingCart.bookNumber } </font> 本书
		</div>

		<table>
			<tr>
				<td>书名</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>数量</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>价格</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<c:forEach items="${sessionScope.ShopingCart.items }" var="item">
				<tr>
					<td>${item.book.title }</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><input class="${item.quantity }" type="text" size="1"
						name="${item.book.bookId }" value="${item.quantity }" /></td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>${item.book.price }</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td><a
						href="getBooksServlet?method=removeBook&pageNo=${param.pageNo }&bookId=${item.book.bookId}"
						class="delete">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" id="totalMoney">总金额为:
					${sessionScope.ShopingCart.totalMoney } 元</td>
			</tr>
			<tr>
				<td colspan="4"><a
					href="getBooksServlet?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
					&nbsp;&nbsp; <a href="getBooksServlet?method=clearBook">清空购物车</a>
					&nbsp;&nbsp; <a href="getBooksServlet?method=forwordPage&page=cash">结账</a> &nbsp;&nbsp;</td>
			</tr>

		</table>

	</center>

</body>
</html>