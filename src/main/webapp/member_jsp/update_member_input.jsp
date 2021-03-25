<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.member.model.*" %>

<%
    MemberBean memberBean = (MemberBean) request.getAttribute("member"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>員工資料修改 - update_emp_input.jsp</title>

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
            <h3>員工資料修改 - update_emp_input.jsp</h3>
            <h4><a href="../member_jsp/select_page.jsp"><img src="../member_jsp/images/back1.gif" width="100"
                                                             height="32" border="0">回首頁</a></h4>
        </td>
    </tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/backend_update" name="form1"
      enctype="multipart/form-data">
    <table>
        <tr>
            <td>會員帳號:<font color=red><b>*</b></font></td>
            <td><%=memberBean.getMember_account()%>
            </td>
        </tr>
        <tr>
            <td>會員密碼:</td>
            <td><input type="TEXT" name="member_password" size="45" value="<%=memberBean.getMember_password()%>"/></td>
        </tr>
        <tr>
            <td>姓名:</td>
            <td><input type="TEXT" name="member_name" size="45" value="<%=memberBean.getMember_name()%>"/></td>
        </tr>
        <tr>
            <td>電話:</td>
            <td><input type="TEXT" name="member_phone" size="45" value="<%=memberBean.getMember_phone()%>"/></td>
        </tr>
        <tr>
            <td>信箱:</td>
            <td><input type="TEXT" name="member_email" size="45" value="<%=memberBean.getMember_email()%>"/></td>
        </tr>
        <tr>
            <td>性別:</td>
            <td><input type="TEXT" name="member_gender" size="45" value="<%=memberBean.getMember_gender()%>"/></td>
        </tr>
        <tr>
            <td>生日:</td>
            <td><input type="TEXT" name="member_birthday" size="45" value="<%=memberBean.getMember_birthday()%>"/></td>
        </tr>
        <tr>
            <td>註冊時間:</td>
            <td><input type="TEXT" name="register_time" size="45" value="<%=memberBean.getRegister_time()%>"/></td>
        </tr>
        <tr>
            <td>註冊方法:</td>
            <td><input type="TEXT" name="register_method" size="45" value="<%=memberBean.getRegister_method()%>"/></td>
        </tr>
        <tr>
            <td>狀態:</td>
            <td><input type="TEXT" name="member_status" size="45" value="<%=memberBean.getMember_status()%>"/></td>
        </tr>
        <tr>
            <td>照片:</td>
<%--            		<td><input type="TEXT" name="member_photo" size="45" 	value="<%=memberBean.getMember_photo()%>" /></td>--%>
            <td>
                <input type="file" name="upfile1">
<%--                <img src="/dessert_shop/member/backend_getPhoto?id=<%=memberBean.getMember_account()%>">--%>
                <input type="image" src="/dessert_shop/member/backend_getPhoto?id=<%=memberBean.getMember_account()%>"name="my_img">


            </td>
        </tr>
        <%--	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />--%>
        <%--	<tr>--%>
        <%--		<td>部門:<font color=red><b>*</b></font></td>--%>
        <%--		<td><select size="1" name="deptno">--%>
        <%--			<c:forEach var="deptVO" items="${deptSvc.all}">--%>
        <%--				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}--%>
        <%--			</c:forEach>--%>
        <%--		</select></td>--%>
        <%--	</tr>--%>

    </table>
    <br>
    <%--<input type="hidden" name="action" value="update">--%>
    <input type="hidden" name="member_account" value="<%=memberBean.getMember_account()%>">
    <%--<input type="hidden" name="register_time" value="<%=memberBean.getRegister_time()%>">--%>
    <%--<input type="hidden" name="register_method" value="<%=memberBean.getRegister_method()%>">--%>
    <%--<input type="hidden" name="member_status" value="<%=memberBean.getMember_status()%>">--%>

    <input type="submit" value="送出修改">
</FORM>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"/>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
        timepicker: false,       //timepicker:true,
        step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
        format: 'Y-m-d',         //format:'Y-m-d H:i:s',
        value: '<%=memberBean.getMember_birthday()%>', // value:   new Date(),
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

</script>
</html>