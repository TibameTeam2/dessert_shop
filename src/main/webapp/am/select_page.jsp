<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Am: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Am: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Am: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAm.jsp'>List</a> All announcement.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="am.do" >
        <b>輸入公告編號 	:</b>
        <input type="text" name="announcement_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="amSvc" scope="page" class="com.announcement_management.model.AnnouncementManagementService" />

  <li>
     <FORM METHOD="post" ACTION="am.do" >
       <b>選擇公告編號:</b>
       <select size="1" name="announcement_id">
         <c:forEach var="announcementManagementBean" items="${amSvc.all}" > 
          <option value="${announcementManagementBean.announcement_id}">${announcementManagementBean.announcement_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="am.do" >
       <b>選擇公告名稱:</b>
       <select size="1" name="announcement_id">
         <c:forEach var="announcementManagementBean" items="${amSvc.all}" > 
          <option value="${announcementManagementBean.announcement_id}">${announcementManagementBean.announcement_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>公告管理</h3>

<ul>
  <li><a href='addAm.jsp'>Add</a> a new announcement.</li>
</ul>

</body>
</html>