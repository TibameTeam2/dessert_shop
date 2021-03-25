<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

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

 th, td { 
 	padding: 5px; 
 	text-align: center; 
 }
 
</style>

</head>
<body bgcolor="lightblue">
	<h3>this is listOneProduct.jsp</h3>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品資訊 - listOneProduct.jsp</h3>
				<h4>
					<a href="select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品編號</th>
			<th>商品名稱</th>
			<th>商品類別</th>
			<th>商品介紹</th>
			<th>商品價格</th>
			<th>現貨數量</th>
			<th>商品狀態</th>
			<th>商品熱量</th>
			<th>商品甜度</th>
			<th>累計星等</th>
			<th>累計評價人數</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<tr>
			<td>${productBean.product_id}</td>
			<td>${productBean.product_name}</td>
			<td>${productBean.product_type}</td>
			<td>${productBean.product_intro}</td>
			<td>${productBean.product_price}</td>
			<td>${productBean.product_available_qty}</td>
			<td>${productBean.product_status}</td>
			<td>${productBean.product_calorie}</td>
			<td>${productBean.degree_of_sweetness}</td>
			<td>${productBean.total_star}</td>
			<td>${productBean.total_review}</td>




<%-- 			<td><%=productBean.getProduct_id()%></td> --%>
<%-- 			<td><%=productBean.getProduct_name()%></td> --%>
<%-- 			<td><%=productBean.getProduct_type()%></td> --%>
<%-- 			<td><%=productBean.getProduct_intro()%></td> --%>
<%-- 			<td><%=productBean.getProduct_price()%></td> --%>
<%-- 			<td><%=productBean.getProduct_available_qty()%></td> --%>
<%-- 			<td><%=productBean.getProduct_status()%></td> --%>
<%-- 			<td><%=productBean.getProduct_calorie()%></td> --%>
<%-- 			<td><%=productBean.getDegree_of_sweetness()%></td> --%>
<%-- 			<td><%=productBean.getTotal_star()%></td> --%>
<%-- 			<td><%=productBean.getTotal_review()%></td> --%>
			<td>
				<form method="post"
					action="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" value="修改"> <input type="hidden"
						name="product_id" value="${productBean.product_id}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</form>
			</td>
			<td>
				<form method="post"
					action="<%=request.getContextPath()%>/product/product.do">
					<input type="submit" value="刪除"> <input type="hidden"
						name="product_id" value="${productBean.product_id}"> <input
						type="hidden" name="action" value="delete">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>