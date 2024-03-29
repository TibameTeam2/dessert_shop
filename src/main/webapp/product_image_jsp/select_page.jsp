<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product Image: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: Yellow;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>Dessert Shop Product Image: Home </h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the home page from Dessert Shop Product Image: Home</p>

	<h3>商品照片查詢：</h3>
	
	<%-- 錯誤表列--%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllProductImage.jsp'>List</a> all ProductImages. <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_image_jsp/product.do">
				<b>輸入商品編號 (如0001):</b> <input type="text" name="product_id"> <input
					type="hidden" name="action" value="getOne_For_Display"> <input
					type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="piSvc" scope="page" class="com.product_image.model.ProductImageService" />
		<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_image_jsp/product.do">
				<b>選擇商品圖片編號:</b> <select size="1" name="image_id">
					<c:forEach var="piBean" items="${piSvc.all}">
						<option value="${piBean.image_id}">${piBean.image_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_image_jsp/product.do">
				<b>選擇商品名稱查詢照片:</b> <select size="1" name="product_name">
					<c:forEach var="productBean" items="${productSvc.all}">
						<option value="${productBean.product_id}">${productBean.product_name}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	<h3>商品圖片管理</h3>

	<ul>
		<li><a href='addProductImage.jsp'>Add</a> a new Product Image.</li>
	</ul>
	
	


</body>
</html>