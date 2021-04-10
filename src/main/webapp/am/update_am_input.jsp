<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.announcement_management.model.*"%>

<%
AnnouncementManagementBean AMB = (AnnouncementManagementBean) request.getAttribute("AMB"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>公告資料修改 - update_am_input.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>公告資料修改 - update_am_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/am/am.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>公告編號:<font color=red><b>*</b></font></td>
				<td><%=AMB.getAnnouncement_id()%></td>
			</tr>
			
			<tr>
				<td>公告名稱:</td>
				<td><input type="TEXT" name="announcement_name" size="45"
					value="<%=AMB.getAnnouncement_name()%>" /></td>
			</tr>
			
			
			<tr>
				<td>公告內容:</td>
				<td><input type="TEXT" name="announcement_content" size="45"
					value="<%=AMB.getAnnouncement_content()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>公告圖片:</td> -->
<!-- 				<td><input type="TEXT" name="announcement_image" size="45" -->
<%-- 					value="<%=AMB.getAnnouncement_image()%>" /></td> --%>
<!-- 			</tr> -->
			
			
			<tr>
				<td>公告圖片:</td>
				<td><input type="file" name="upfile1"/></td>
			</tr>
			
			
			
			<tr>
				<td>公告日期:</td>
				<td><input name="announcement_time" id="f_date1" type="text"
				value="<%=AMB.getAnnouncement_time()%>" /></td>
			</tr>
			<tr>
				<td>公告種類:</td>
				<td><input type="TEXT" name="announcement_type" size="45"
					value="<%=AMB.getAnnouncement_type()%>" /></td>
			</tr>
			<tr>
				<td>公告狀態:</td>
				<td><input type="TEXT" name="announcement_status" size="45"
					value="<%=AMB.getAnnouncement_status()%>" /></td>
			</tr>
			
			<tr>
				<td>員工:</td>
				<td><input type="TEXT" name="employee_account" size="45"
					value="<%=AMB.getEmployee_account()%>" /></td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td>薪水:</td> -->
<!-- 				<td><input type="TEXT" name="sal" size="45" -->
<%-- 					value="<%=empVO.getSal()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>獎金:</td> -->
<!-- 				<td><input type="TEXT" name="comm" size="45" -->
<%-- 					value="<%=empVO.getComm()%>" /></td> --%>
<!-- 			</tr> -->

<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
<%-- 				class="com.dept.model.DeptService" /> --%>
<!-- 			<tr> -->
<!-- 				<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 				<td><select size="1" name="deptno"> -->
<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 							<option value="${deptVO.deptno}" --%>
<%-- 								${(empVO.deptno==deptVO.deptno)?'selected':'' }>${deptVO.dname} --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="announcement_id" value="<%=AMB.getAnnouncement_id()%>">
			 <input type="submit" value="送出修改">
	</FORM>
</body>


</html>