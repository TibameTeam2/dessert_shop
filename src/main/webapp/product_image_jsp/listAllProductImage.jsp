<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_image.model.*"%>


<%
	ProductImageService piSvc = new ProductImageService();
	List<ProductImageBean> list = piSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有商品圖片 - lisAllProductImage.jsp</title>

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
	<h3>this is listAllProductImage.jsp</h3>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品圖片 - listAllProductImage.jsp</h3>
				<h4>
					<a href="../product_image_jsp/select_page.jsp">回首頁</a>
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
			<th>商品圖片編號</th>			
			<th>商品圖片</th>

			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="piBean" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${piBean.product_id}</td>
				<td>${piBean.image_id}</td>
		
				<td><img
					src="<%=request.getContextPath()%>/product_jsp/product.do?action=getProductImage&id=${piBean.product_id}"
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