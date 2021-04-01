<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_image.model.*"%>


<%
	ProductImageBean piBean = (ProductImageBean) request.getAttribute("piBean");
%>

<!DOCTYPE html>
<html>
<head>

<title>商品圖片資訊 - listOneProductImage.jsp</title>

<style>
table#table-1 {
	background-color: yellow;
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
	<h3>this is listOneProductImage.jsp</h3>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資訊 - listOneProductImage.jsp</h3>
				<h4>
					<a href="../product_image_jsp/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品編號</th>
			<th>商品圖片編號</th>
			
			<th>商品圖片</th>

		</tr>
		<tr>
			<td><%=piBean.getProduct_id()%></td>
			<td><%=piBean.getImage_id()%></td>
	
			<td><img
				src="<%=request.getContextPath()%>/product_jsp/product.do?action=getProductImage&id=${productBean.product_id}">
			</td>
			
	</table>

</body>
</html>