<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon_code.model.*"%>

<%
CouponCodeBean CCB = (CouponCodeBean) request.getAttribute("CCB"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�u�f�X��ƭק� - update_cc_input.jsp</title>

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
				<h3>�u�f�X��ƭק� - update_cc_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon_code_jsp/cc.do" name="form1" enctype="multipart/form-data">
		<table>           
			<tr>
				<td>�u�f�X�s��:<font color=red><b>*</b></font></td>
				<td><%=CCB.getCoupon_code_id()%></td>
			</tr>
			
			<tr>
				<td>�u�f�X�W��:</td>
				<td><input type="TEXT" name="coupon_code" size="45"
					value="<%=CCB.getCoupon_code()%>" /></td>
			</tr>
			
			<tr>
				<td>�u�f�X�ͮĤ��:</td>
				<td><input name="coupon_code_effective_date" id="f_date1" type="text"
				value="<%=CCB.getCoupon_code_effective_date()%>" /></td>
			</tr>
			
			<tr>
				<td>�u�f�X�̫�ĥΤ��:</td>
				<td><input name="coupon_code_expire_date" id="f_date1" type="text"
				value="<%=CCB.getCoupon_code_expire_date()%>" /></td>
			</tr>
			
			
			<tr>
				<td>�u�f�X���e:</td>
				<td><input type="TEXT" name="coupon_code_text_content" size="45"
					value="<%=CCB.getCoupon_code_text_content()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>���i�Ϥ�:</td> -->
<!-- 				<td><input type="TEXT" name="announcement_image" size="45" -->
<%-- 					value="<%=AMB.getAnnouncement_image()%>" /></td> --%>
<!-- 			</tr> -->
			
			
<!-- 			<tr> -->
<!-- 				<td>���i�Ϥ�:</td> -->
<!-- 				<td><input type="file" name="upfile1"/></td> -->
<!-- 			</tr> -->
			
			
<!-- 			<tr> -->
<!-- 				<td>���i���:</td> -->
<!-- 				<td><input name="announcement_time" id="f_date1" type="text" -->
<%-- 				value="<%=AMB.getAnnouncement_time()%>" /></td> --%>
<!-- 			</tr> -->
			<tr>
				<td>�u�f�X??????:</td>
				<td><input type="TEXT" name="coupon_code_text" size="45"
					value="<%=CCB.getCoupon_code_text()%>" /></td>
			</tr>
			<tr>
				<td>�u�f�X�������:</td>
				<td><input type="TEXT" name="discount_type" size="45"
					value="<%=CCB.getDiscount_type()%>" /></td>
			</tr>
			
			<tr>
				<td>���u:</td>
				<td><input type="TEXT" name="employee_account" size="45"
					value="<%=CCB.getEmployee_account()%>" /></td>
			</tr>
			
<!-- 			<tr> -->
<!-- 				<td>�~��:</td> -->
<!-- 				<td><input type="TEXT" name="sal" size="45" -->
<%-- 					value="<%=empVO.getSal()%>" /></td> --%>
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>����:</td> -->
<!-- 				<td><input type="TEXT" name="comm" size="45" -->
<%-- 					value="<%=empVO.getComm()%>" /></td> --%>
<!-- 			</tr> -->

<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
<%-- 				class="com.dept.model.DeptService" /> --%>
<!-- 			<tr> -->
<!-- 				<td>����:<font color=red><b>*</b></font></td> -->
<!-- 				<td><select size="1" name="deptno"> -->
<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 							<option value="${deptVO.deptno}" --%>
<%-- 								${(empVO.deptno==deptVO.deptno)?'selected':'' }>${deptVO.dname} --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="coupon_code_id" value="<%=CCB.getCoupon_code_id()%>">
			 <input type="submit" value="�e�X�ק�">
	</FORM>
</body>


</html>