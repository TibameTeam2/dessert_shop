<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon_code.model.*"%>

    
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CouponCodeService ccSvc = new CouponCodeService();
	List<CouponCodeBean> list = ccSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>


<html>
<head>
<title>所有優惠碼資料 - listAllCc.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
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
</style>

<style>
table {
	width: 800px;
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
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有優惠碼資料 - listAllCc.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
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
			<th>優惠碼編號</th>
			<th>優惠碼</th>
			<th>優惠碼生效日期</th>
			<th>優惠碼有效日期</th>
			<th>優惠碼文字內容</th>
			<th>優惠碼內容</th>
			<th>優惠碼折價種類</th>
			<th>編輯公告員工</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="couponCodeBean" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${couponCodeBean.coupon_code_id}</td>
				<td>${couponCodeBean.coupon_code}</td>
				<td>${couponCodeBean.coupon_code_effective_date}</td>
				<td>${couponCodeBean.coupon_code_expire_date}</td>
<%-- 				<td>${AnnouncementManagementBean.announcement_image}</td> --%>

<%-- 				<td>  <% AnnouncementManagementBean AMB =(AnnouncementManagementBean)pageContext.getAttribute("announcementManagementBean");%> --%>
<%--                     	 <% if (AMB.getAnnouncement_image() != null){ --%>
<%--                         	 System.out.println(AMB.getAnnouncement_image());%> --%>
<%--                       	   <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(AMB.getAnnouncement_image())%>"> --%>
<%--                       	   <%}%> --%>
<!--                	</td> -->

				<td>${couponCodeBean.coupon_code_text_content}</td>
				<td>${couponCodeBean.coupon_code_text}</td>
				<td>${couponCodeBean.discount_type}</td>
				<td>${couponCodeBean.employee_account}</td>
				
				
				
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/coupon_code_jsp/cc.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden"
							name="coupon_code_id" value="${couponCodeBean.coupon_code_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/coupon_code_jsp/cc.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="coupon_code_id" value="${couponCodeBean.coupon_code_id}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>