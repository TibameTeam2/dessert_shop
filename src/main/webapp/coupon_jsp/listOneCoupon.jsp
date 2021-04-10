<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64" %>>
<%@ page import="com.coupon.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
CouponBean couponBean = (CouponBean) request.getAttribute("couponBean"); //EmpServlet.java(Concroller), 存入req的empVO物件
System.out.println("JSP:"+ couponBean);
%>

<html>
<head>
<title>優惠券資料 - listOneCoupon.jsp</title>

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
		 <h3>優惠券資料 - ListOneCoupon.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
	<tr>
		<td><%=couponBean.getCoupon_id()%></td>
		<td><%=couponBean.getMember_account()%></td>
		<td><%=couponBean.getCoupon_sending_time()%></td>
		<td><%=couponBean.getCoupon_effective_date()%></td>
		<td><%=couponBean.getCoupon_expire_date()%></td>
		<td><%=couponBean.getCoupon_text_content()%></td>
		<td><%=couponBean.getCoupon_content()%></td>
		<td><%=couponBean.getDiscount_type()%></td>
		<td><%=couponBean.getCoupon_status()%></td>
		<td><%=couponBean.getEmployee_account()%></td>
		<td><%=couponBean.getCoupon_code_id()%></td>
	</tr>
</table>

</body>
</html>