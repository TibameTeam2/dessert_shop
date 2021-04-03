<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	ProductBean productBean = (ProductBean) request.getAttribute("productBean");
%>

<!DOCTYPE html>
<html>
<head>

<title>商品資訊 - listOneProduct.jsp</title>

<style>
table#table-1 {
	background-color: violet;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th{
	padding: 5px;
	text-align: center;
	white-space: nowrap;
}
td {
	padding: 5px;
	text-align: center;
}
img {
	width: 150px;
	height: 150px;
}
</style>

</head>
<body bgcolor="white">
	<h3>this is listOneProduct.jsp</h3>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資訊 - listOneProduct.jsp</h3>
				<h4>
					<a href="../product_jsp/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品主分類</th>
			<th>商品子分類</th>
			<th>商品介紹</th>
			<th>商品成份</th>
			<th>商品價格</th>
			<th>現貨數量</th>
			<th>商品狀態</th>
			<th>賞味天數</th>
			<th>商品熱量</th>
			<th>商品甜度</th>
			<th>累計星等</th>
			<th>累計評價次數</th>
			<th>累計銷售</th>

			<th>商品圖片</th>

			<!-- 			<th>修改</th> -->
			<!-- 			<th>刪除</th> -->
		</tr>
		<tr>
			<td><%=productBean.getProduct_id()%></td>
			<td><%=productBean.getProduct_name()%></td>
			<td><%=productBean.getProduct_type()%></td>
			<td><%=productBean.getProduct_subtype()%></td>
			<td><%=productBean.getProduct_intro()%></td>
			<td><%=productBean.getProduct_ingredient()%></td>
			<td><%=productBean.getProduct_price()%></td>
			<td><%=productBean.getProduct_available_qty()%></td>
			<td><c:choose>
					<c:when test="<%=productBean.getProduct_status() == 0%>">未上架</c:when>
					<c:when test="<%=productBean.getProduct_status() == 1%>">上架中</c:when>
					<c:otherwise>未知錯誤</c:otherwise>
				</c:choose></td>
			<td><%=productBean.getExpiry_after_buying()%></td>
			<td><%=productBean.getProduct_calorie()%></td>
			<td><%=productBean.getDegree_of_sweetness()%></td>
			<td>
				<fmt:formatNumber type="number" value="${productBean.total_star/productBean.total_review}" maxFractionDigits="1"/>
			</td>
			<td><%=productBean.getTotal_review()%></td>
			<td><%=productBean.getTotal_purchase()%></td>

			<td><img
				src="<%=request.getContextPath()%>/product_jsp/product.do?action=getProductImage&id=${productBean.image_id}">
			</td>
			
	</table>

</body>
</html>