<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement_management.model.*"%>

    
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AnnouncementManagementService amSvc = new AnnouncementManagementService();
	List<AnnouncementManagementBean> list = amSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>


<html>
<head>
<title>所有公告資料 - listAllAm.jsp</title>

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
				<h3>所有公告資料 - listAllAm.jsp</h3>
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
			<th>公告編號</th>
			<th>公告名稱</th>
			<th>公告內容</th>
			<th>公告圖片</th>
			<th>公告時間</th>
			<th>公告種類</th>
			<th>公告狀態</th>
			<th>編輯公告員工</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="announcementManagementBean" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${announcementManagementBean.announcement_id}</td>
				<td>${announcementManagementBean.announcement_name}</td>
				<td>${announcementManagementBean.announcement_content}</td>
<%-- 				<td>${AnnouncementManagementBean.announcement_image}</td> --%>

				<td>  <% AnnouncementManagementBean AMB =(AnnouncementManagementBean)pageContext.getAttribute("announcementManagementBean");%>
                    	 <% if (AMB.getAnnouncement_image() != null){
                        	 System.out.println(AMB.getAnnouncement_image());%>
                      	   <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(AMB.getAnnouncement_image())%>">
                      	   <%}%>
               	</td>

				<td>${announcementManagementBean.announcement_time}</td>
				<td>${announcementManagementBean.announcement_type}</td>
				<td>${announcementManagementBean.announcement_status}</td>
				<td>${announcementManagementBean.employee_account}</td>
				
				
				
				
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/am/am.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden"
							name="announcement_id" value="${announcementManagementBean.announcement_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/am/am.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="announcement_id" value="${announcementManagementBean.announcement_id}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>