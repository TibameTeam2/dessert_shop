<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon.model.*"%>

<%
CouponBean couponBean = (CouponBean) request.getAttribute("couponBean");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>優惠券資料新增 - addEmp.jsp</title>

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
	<tr><td>
		 <h3>優惠券資料新增 - addCoupon.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupon_jsp/coupon.do" name="form1"
enctype="multipart/form-data">
<table>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="member_account" size="45" 
			 value="<%= (couponBean==null)? "" : couponBean.getMember_account()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠券寄送時間:</td>
		<td><input name="coupon_sending_time" id="f_date1" type="text"></td>
	</tr>
	
	<tr>
		<td>優惠券生效日:</td>
		<td><input name="coupon_effective_date" id="f_date2" type="text"></td>
	</tr>
	
	<tr>
		<td>優惠券到期日:</td>
		<td><input name="coupon_expire_date" id="f_date3" type="text"></td>
	</tr>
	
	<tr>
		<td>優惠券文字內容:</td>
		<td><input type="TEXT" name="coupon_text_content" size="45" 
			 value="<%= (couponBean==null)? "" : couponBean.getCoupon_text_content()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠券內容:</td>
		<td><input type="TEXT" name="coupon_content" size="45"
			 value="<%= (couponBean==null)? "" : couponBean.getCoupon_content()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠方式:</td>
		<td><input type="TEXT" name="discount_type" size="45"
			 value="<%= (couponBean==null)? "" : couponBean.getDiscount_type()%>" /></td>
	</tr>
	<tr>
		<td>優惠券狀態:</td>
		<td><input type="TEXT" name="coupon_status" size="45"
			 value="<%= (couponBean==null)? "" : couponBean.getCoupon_status()%>" /></td>
	</tr>

	<tr>
		<td>員工帳號:</td>
		<td><input type="TEXT" name="employee_account" size="45"
			 value="<%= (couponBean==null)? "" : couponBean.getEmployee_account()%>" /></td>
	</tr>
	
	<tr>
		<td>優惠碼編號:</td>
		<td><input type="TEXT" name="coupon_code_id" size="45"
			 value="<%= (couponBean==null)? "" : couponBean.getCoupon_code_id()%>" /></td>
	</tr>
	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp coupon_sending_time = null;
  try {
	  coupon_sending_time = couponBean.getCoupon_sending_time();
   } catch (Exception e) {
	   coupon_sending_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Timestamp coupon_effective_date = null;
  try {
	  coupon_effective_date = couponBean.getCoupon_effective_date();
   } catch (Exception e) {
	   coupon_effective_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

<% 
  java.sql.Timestamp coupon_expire_date = null;
  try {
	  coupon_expire_date = couponBean.getCoupon_expire_date();
   } catch (Exception e) {
	   coupon_expire_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=coupon_sending_time%>
	       ', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	             '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=coupon_effective_date%>
	       ', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=coupon_expire_date%>
	       ', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
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

        
        //      2.以下為某一天之後的日期無法選擇
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


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
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
        
        
        // 顯示上傳圖片
        $(".upload_img").on("change", function(){
        	var fr = new FileReader();
        	var file = $(".upload_img")[0].files[0];
        	fr.readAsDataURL(file);
        	$(fr).on("load",function(){
        		$(".show_img").attr("src", this.result);
        	});
        	
        });
        
        
        
</script>
</html>