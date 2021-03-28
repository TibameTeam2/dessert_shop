<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product: Home</title>

<style>
	table#table-1{
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
	}

</style>


</head>
<body bgcolor='white'>
	<table id="table-1">
		<tr>
			<td><h3>Dessert Shop Product: Home</h3>
				<h4>(MVC)</h4></td>
		</tr>
	</table>
	
	<p>This is the home page for Dessert Shop Product: Home</p>
	
	<h3>商品查詢</h3>
	<%-- 錯誤表列 --%>   					<%-- 這啥???????? --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllProduct.jsp'>List</a> all Products. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="product.do">
				<b>輸入商品編號 (如0001):</b> <input type="text" name="product_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

		<li>
			<FORM METHOD="post" ACTION="product.do">
				<b>選擇商品編號:</b> <select size="1" name="product_id">
					<c:forEach var="proBean" items="${productSvc.all}">
						<option value="${proBean.product_id}">${proBean.product_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="product.do">
				<b>選擇商品名稱:</b> <select size="1" name="product_id">
					<c:forEach var="proBean" items="${productSvc.all}">
						<option value="${proBean.product_id}">${proBean.product_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	


</body>
</html>