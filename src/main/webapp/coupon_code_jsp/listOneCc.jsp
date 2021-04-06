<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>>
<%@ page import="com.coupon_code.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CouponCodeBean CCB = (CouponCodeBean) request.getAttribute("CCB"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("JSP:"+CCB);
%>

<html>
<head>
<title>優惠碼資料 - listOneCc.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>優惠碼資料 - ListOneCc.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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
		</tr>
		<tr>
						<td><%=CCB.getCoupon_code_id() %></td>
						<td><%=CCB.getCoupon_code() %></td>
						<td><%=CCB.getCoupon_code_effective_date() %>}</td>
<%-- 						<td><%=AMB.getAnnouncement_image() %></td> --%>
						
<!-- 						<td> -->
<%-- 							<% if (AMB.getAnnouncement_image() != null){ --%>
<%--                          		System.out.println(AMB.getAnnouncement_image());%> --%>
<%--                        			<img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(AMB.getAnnouncement_image())%>"> --%>
<%--                       		  <%}%> --%>
<!-- 						</td> -->
						
						<td><%=CCB.getCoupon_code_expire_date() %></td>
						<td><%=CCB.getCoupon_code_text_content() %></td>
						<td><%=CCB.getCoupon_code_text() %></td>
						<td><%=CCB.getDiscount_type() %></td>
						<td><%=CCB.getEmployee_account() %></td>
						
						
						
<%-- 			<td>${AMB.announcement_id}</td> --%>
<%-- 			<td>${AMB.announcement_name}</td> --%>
<%-- 			<td>${AMB.announcement_content}</td> --%>
<%-- 			<td>${AMB.announcement_image}</td> --%>
<%-- 			<td>${AMB.announcement_time}</td> --%>
<%-- 			<td>${AMB.announcement_type}</td> --%>
<%-- 			<td>${AMB.announcement_status}</td> --%>
<%-- 			<td>${AMB.employee_account}</td> --%>
		</tr>
	</table>

</body>
</html>