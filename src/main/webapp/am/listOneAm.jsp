<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>>
<%@ page import="com.announcement_management.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
AnnouncementManagementBean AMB = (AnnouncementManagementBean) request.getAttribute("AMB"); //EmpServlet.java(Concroller), 存入req的empVO物件
	System.out.println("JSP:"+AMB);
%>

<html>
<head>
<title>公告資料 - listOneAm.jsp</title>

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
				<h3>公告資料 - ListOneAm.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>公告編號</th>
			<th>公告名稱</th>
			<th>公告內容</th>
			<th>公告圖片</th>
			<th>公告時間</th>
			<th>公告種類</th>
			<th>公告狀態</th>
			<th>編輯公告員工</th>
		</tr>
		<tr>
						<td><%=AMB.getAnnouncement_id() %></td>
						<td><%=AMB.getAnnouncement_name() %></td>
						<td><%=AMB.getAnnouncement_content() %>}</td>
<%-- 						<td><%=AMB.getAnnouncement_image() %></td> --%>
						
						<td>
							<% if (AMB.getAnnouncement_image() != null){
                         		System.out.println(AMB.getAnnouncement_image());%>
                       			<img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(AMB.getAnnouncement_image())%>">
                      		  <%}%>
						</td>
						
						<td><%=AMB.getAnnouncement_time() %></td>
						<td><%=AMB.getAnnouncement_type() %></td>
						<td><%=AMB.getAnnouncement_status() %></td>
						<td><%=AMB.getEmployee_account() %></td>
						
						
						
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