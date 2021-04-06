<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
CouponService cSvc = new CouponService();
    List<CouponBean> list = cSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有優惠券資料 - listAllCoupon.jsp</title>

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
	<tr><td>
		 <h3>所有優惠券資料 - listAllCoupon.jsp</h3>
		 <h4><a href="../coupon_jsp/select_page.jsp"><img src="../employee_jsp/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>優惠券編號</th>
		<th>會員帳號</th>
		<th>優惠券寄送時間</th>
		<th>優惠券生效日</th>
		<th>優惠券到期日</th>
		<th>優惠券文字內容</th>
		<th>優惠券內容</th>
		<th>優惠方式</th>
		<th>優惠券狀態</th>
		<th>員工帳號</th>
		<th>優惠碼編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="couponBean" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${couponBean.coupon_id}</td>
			<td>${couponBean.member_account}</td>
			<td>${couponBean.coupon_sending_time}</td>
			<td>${couponBean.coupon_effective_date}</td>
			<td>${couponBean.coupon_expire_date}</td>
			<td>${couponBean.coupon_text_content}</td>
			<td>${couponBean.coupon_content}</td>
			<td>${couponBean.discount_type}</td>
			<td>${couponBean.coupon_status}</td>

			<td>${couponBean.employee_account}</td> 
			<td>${couponBean.coupon_code_id}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon_jsp/coupon.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="employee_account"  value="${empBean.employee_account}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon_jsp/coupon.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="employee_account"  value="${empBean.employee_account}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>