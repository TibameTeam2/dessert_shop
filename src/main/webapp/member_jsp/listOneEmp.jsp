<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.MemberBean" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemberBean member = (MemberBean) request.getAttribute("member"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="../member_jsp/select_page.jsp"><img src="../member_jsp/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員帳號</th>
		<th>會員密碼</th>
		<th>姓名</th>
		<th>電話</th>
		<th>信箱</th>
		<th>性別</th>
		<th>生日</th>
		<th>註冊時間</th>
		<th>註冊方法</th>
		<th>狀態</th>
		<th>照片</th>
	</tr>
	<tr>
		<td><%=member.getMember_account()%></td>
		<td><%=member.getMember_password()%></td>
		<td><%=member.getMember_name()%></td>
		<td><%=member.getMember_phone()%></td>
		<td><%=member.getMember_email()%></td>
		<td><%=member.getMember_gender()%></td>
		<td><%=member.getMember_birthday()%></td>
		<td><%=member.getRegister_time()%></td>
		<td><%=member.getRegister_method()%></td>
		<td><%=member.getMember_status()%></td>
		<td>
			<img src="<%=request.getContextPath()%>/member/backend_getPhoto?id=${member.member_account}">
		</td>
	</tr>
</table>

</body>
</html>