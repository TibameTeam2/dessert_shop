<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>
<%@ page import="java.util.Base64" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberBean> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>



<html>
<head>
    <title>所有員工資料 - listAllEmp.jsp</title>

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
        img{
            height: 150px;
            weight: 150px;
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
    <tr>
        <td>
            <h3>所有員工資料 - listAllEmp.jsp</h3>
            <h4><a href="../member_jsp/select_page.jsp"><img src="../member_jsp/images/back1.gif" width="100"
                                                             height="32" border="0">回首頁</a></h4>
        </td>
    </tr>
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
        <th>修改</th>
        <th>刪除</th>
    </tr>
    <%@ include file="page1.file" %>


    <c:forEach var="memberBean" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

        <tr>
            <td>${memberBean.member_account}</td>
            <td>${memberBean.member_password}</td>
            <td>${memberBean.member_name}</td>
            <td>${memberBean.member_phone}</td>
            <td>${memberBean.member_email}</td>
            <td>
                <c:choose>
                    <c:when test="${memberBean.member_gender == 0}">女</c:when>
                    <c:when test="${memberBean.member_gender == 1}">男</c:when>
                    <c:otherwise>未知錯誤</c:otherwise>
                </c:choose>
            </td>
            <td>${memberBean.member_birthday}</td>
            <td>${memberBean.register_time}</td>
            <td>${memberBean.register_method}</td>
            <td>
                <c:choose>
                    <c:when test="${memberBean.member_status == 0}">未啟用</c:when>
                    <c:when test="${memberBean.member_status == 1}">已啟用</c:when>
                    <c:otherwise>未知錯誤</c:otherwise>
                </c:choose>
            </td>




            <td>
                <img src="<%=request.getContextPath()%>/member/backend_getPhoto?id=${memberBean.member_account}">
<%--                        <% MemberBean member=(MemberBean)pageContext.getAttribute("memberBean");%>--%>
<%--                        <% if (member.getMember_photo() != null){--%>
<%--                            System.out.println(member.getMember_photo());%>--%>
<%--                            <img src="data:image/png;base64,<%=Base64.getEncoder().encodeToString(member.getMember_photo())%>">--%>
<%--                        <%}%>--%>
            </td>

            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/backend_getOne_For_Update"
                      style="margin-bottom: 0px;">
                    <input type="submit" value="修改">
                    <input type="hidden" name="member_account" value="${memberBean.member_account}">
                        <%--			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>--%>
                </FORM>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/backend_delete"
                      style="margin-bottom: 0px;">
                    <input type="submit" value="刪除">
                    <input type="hidden" name="member_account" value="${memberBean.member_account}">
                        <%--			     <input type="hidden" name="action" value="delete"></FORM>--%>
                </FORM>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>