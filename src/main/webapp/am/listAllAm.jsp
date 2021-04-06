<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement_management.model.*"%>

    
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	AnnouncementManagementService amSvc = new AnnouncementManagementService();
	List<AnnouncementManagementBean> list = amSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>


<html>
<head>
<title>�Ҧ����i��� - listAllAm.jsp</title>

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

	<h4>�����m�߱ĥ� EL ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>�Ҧ����i��� - listAllAm.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>���i�s��</th>
			<th>���i�W��</th>
			<th>���i���e</th>
			<th>���i�Ϥ�</th>
			<th>���i�ɶ�</th>
			<th>���i����</th>
			<th>���i���A</th>
			<th>�s�褽�i���u</th>
			<th>�ק�</th>
			<th>�R��</th>
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
						<input type="submit" value="�ק�"> 
						<input type="hidden"
							name="announcement_id" value="${announcementManagementBean.announcement_id}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/am/am.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
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