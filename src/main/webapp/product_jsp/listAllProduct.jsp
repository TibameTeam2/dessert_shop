<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductBean> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有商品資料 - lisAllProduct.jsp</title>

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

th {
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
	<h3>this is listAllProduct.jsp</h3>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品資訊 - listAllProduct.jsp</h3>
				<h4>
					<a href="../product_jsp/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

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

			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="productBean" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${productBean.product_id}</td>
				<td>${productBean.product_name}</td>
				<td>${productBean.product_type}</td>
				<td>${productBean.product_subtype}</td>
				<td>${productBean.product_intro}</td>
				<td>${productBean.product_ingredient}</td>
				<td>${productBean.product_price}</td>
				<td>${productBean.product_available_qty}</td>
				<td><c:choose>
						<c:when test="${productBean.product_status == 0}">未上架</c:when>
						<c:when test="${productBean.product_status == 1}">上架中</c:when>
						<c:otherwise>未知錯誤</c:otherwise>
					</c:choose></td>
				<td>${productBean.expiry_after_buying}</td>
				<td>${productBean.product_calorie}</td>
				<td>${productBean.degree_of_sweetness}</td>
				<td><fmt:formatNumber type="number"
						value="${productBean.total_star/productBean.total_review}" maxFractionDigits="1" />
				</td>
				<td>${productBean.total_review}</td>
				<td>${productBean.total_purchase}</td>

				<%-- 				<td><% ProductImageBean productImageBean =(ProductImageBean)pageContext.getAttribute("productImageBean");%> --%>
				<%--                      <% if (productBean.getEmployee_photo() != null){ --%>
				<%--                          System.out.println(empBean.getEmployee_photo());%> --%>
				<%--                        <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(empBean.getEmployee_photo())%>"> --%>
				<%--                       <%}%> --%>
				<!--            		 </td> -->

				<td><img
					src="<%=request.getContextPath()%>/product_jsp/product.do?action=getProductImage&id=${productBean.product_id}"
					alt="photo"></td>

				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product_jsp/product.do">
						<input type="submit" value="修改"> <input type="hidden"
							name="product_id" value="${productBean.product_id}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/product_jsp/product.do">
						<input type="submit" value="刪除"> <input type="hidden"
							name="product_id" value="${productBean.product_id}"> <input
							type="hidden" name="action" value="delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>