<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>>
<%@ page import="com.employee.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmployeeBean empBean = (EmployeeBean) request.getAttribute("empBean"); //EmpServlet.java(Concroller), 存入req的empVO物件
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
		 <h3>員工資料 - ListOneEmployee.jsp</h3>
		 <h4><a href="../employee_jsp/select_page.jsp"><img src="../employee_jsp/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>員工帳號</th>
		<th>員工姓名</th>
		<th>員工密碼</th>
		<th>員工職位</th>
		<th>員工照片</th>
		<th>雇用日期</th>
		<th>員工狀態</th>
	</tr>
	<tr>
		<td><%=empBean.getEmployee_account()%></td>
		<td><%=empBean.getEmployee_name()%></td>
		<td><%=empBean.getEmployee_password()%></td>
		<td><%=empBean.getEmployee_position()%></td>
		<td>
            <% if (empBean.getEmployee_photo() != null){
                System.out.println(empBean.getEmployee_photo());%>
              <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(empBean.getEmployee_photo())%>">
             <%}%>
		</td>
		<td><%=empBean.getHire_date()%></td>
		<td><%=empBean.getEmployee_status()%></td>
	</tr>
</table>

</body>
</html>