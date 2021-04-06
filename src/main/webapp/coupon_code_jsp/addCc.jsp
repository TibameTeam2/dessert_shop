<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon_code.model.*"%>

<%
CouponCodeBean CCB = (CouponCodeBean) request.getAttribute("CCB");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>�u�f�X��Ʒs�W - addCc.jsp</title>

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
				<h3>�u�f�X��Ʒs�W - addCc.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��Ʒs�W:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="cc.do" name="form1" enctype="multipart/form-data">
		<table>                 
			<tr>
				<td>�u�f�X:</td>
				<td><input type="TEXT" name="coupon_code" size="45"
					value="<%=(CCB == null) ? "" : CCB.getCoupon_code()%>" /></td>
			</tr>
			<tr>
				<td>�u�f�X�ͮĤ��:</td>
				<td><input name="coupon_code_effective_date" id="f_date1" type="text"
					value="<%=(CCB == null) ? "�u�f�X�ͮĤ��" : CCB.getCoupon_code_effective_date()%>" /></td>
			</tr>
			
			<tr>
				<td>�u�f�X������:</td>
				<td><input name="coupon_code_expire_date" id="f_date2" type="text"
					value="<%=(CCB == null) ? "�u�f�X������" : CCB.getCoupon_code_expire_date()%>" /></td>
			</tr>
			
			<tr>
				<td>�u�f�X��r���e:</td>
				<td><input type="TEXT" name="coupon_code_text_content" size="45"
					value="<%=(CCB == null) ? "" : CCB.getCoupon_code_text_content()%>" /></td>
			</tr>
			
			<tr>
				<td>�u�f�X���e:</td>
				<td><input type="TEXT" name="coupon_code_text" size="45"
					value="<%=(CCB == null) ? "" : CCB.getCoupon_code_text()%>" /></td>
			</tr>

 		
			<tr>
				<td>�u�f�X�������:</td>
				<td><input type="TEXT" name="discount_type" size="45"
					value="<%=(CCB == null) ? "�п�J�Ʀr + f" : CCB.getDiscount_type()%>" /></td>
			</tr>


			<tr>
				<td>���u:</td>
				<td><input type="TEXT" name="employee_account" size="45"
					value="<%=(CCB == null) ? "" : CCB.getEmployee_account()%>" /></td>
			</tr>

			<%-- 			<jsp:useBean id="empSvc" scope="page" class="com.employee.model.EmployeeService" /> --%>
			<!-- 			<tr> -->
			<!-- 				<td>���u:<font color=red><b>*</b></font></td> -->
			<!-- 				<td><select size="1" name="employee_account"> -->
			<%-- 						<c:forEach var="EmployeeBean" items="${empSvc.all}"> --%>
			<%-- 							<option value="${EmployeeBean.employee_account}" ${(CCB.employee_account==EmployeeBean.employee_account)? 'selected':'' }>${EmployeeBean.employee_name} --%>
			<%-- 						</c:forEach> --%>
			<!-- 				</select></td> -->
			<!-- 			</tr> -->

			<%-- 			<jsp:useBean id="deptSvc" scope="page" --%>
			<%-- 				class="com.dept.model.DeptService" /> --%>
			<!-- 			<tr> -->
			<!-- 				<td>���u:<font color=red><b>*</b></font></td> -->
			<!-- 				<td><select size="1" name="deptno"> -->
			<%-- 						<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 							<option value="${deptVO.deptno}" --%>
			<%-- 								${(empVO.deptno==deptVO.deptno)? 'selected':'' }>${deptVO.dname} --%>
			<%-- 						</c:forEach> --%>
			<!-- 				</select></td> -->
			<!-- 			</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="�e�X�s�W">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<%
	java.sql.Timestamp coupon_code_effective_date = null;
	try {
		coupon_code_effective_date = CCB.getCoupon_code_effective_date();
	} catch (Exception e) {
		coupon_code_effective_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<%
	java.sql.Timestamp coupon_code_expire_date = null;
	try {
		coupon_code_expire_date = CCB.getCoupon_code_expire_date();
	} catch (Exception e) {
		coupon_code_expire_date = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=coupon_code_effective_date%>
	       ', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=coupon_code_expire_date%>
	       ',  // value:   new Date(),
	 //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
     //maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>