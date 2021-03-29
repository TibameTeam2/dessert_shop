<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
  ProductBean productBean = (ProductBean) request.getAttribute("productBean"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"  charset="UTF-8">
<title> 修改商品資訊 - update_product_input.jsp</title>

<style>
table#table-1 {
	background-color: pink;
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
<body bgcolor="white">
	
	<table id="table-1">
		<tr>
			<td>
				<h3>修改商品資訊 - update_product_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/chocoMacaron1.jpg"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>4
	</c:if>

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_jsp/product.do" name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=productBean.getProduct_id()%></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="product_name" size="45"
					value="<%=productBean.getProduct_name()%>" /></td>
			</tr>
			<tr>
				<td>商品種類:</td>
				<td><select size="1" name="product_type">
						<% String category = productBean.getProduct_type() + ":" + productBean.getProduct_subtype();
							System.out.println(category);%>
<%-- 						<%if(category.equals("蛋糕:千層蛋糕")){%> --%>
<!-- 						selected -->
<%-- 						<%}%>				 //只能此種寫法 --%>
						
						<option value="">請選擇商品種類						
						<optgroup label="蛋糕">
							<option value="蛋糕:千層蛋糕"<%if(category.equals("蛋糕:千層蛋糕")){%>
							selected
							<%}%>>千層蛋糕</option>
							<option value="蛋糕:杯子蛋糕"<%if(category.equals("蛋糕:杯子蛋糕")){%>
							selected
							<%}%>>杯子蛋糕</option>
							<option value="蛋糕:起士蛋糕"<%if(category.equals("蛋糕:起士蛋糕")){%>
							selected
							<%}%>>起士蛋糕</option>
							<option value="蛋糕:磅蛋糕"<%if(category.equals("蛋糕:磅蛋糕")){%>
							selected
							<%}%>>磅蛋糕</option>
							<option value="蛋糕:蛋糕捲"<%if(category.equals("蛋糕:蛋糕捲")){%>
							selected
							<%}%>>蛋糕捲</option>
						</optgroup>
						<optgroup label="巧克力">
							<option value="巧克力:生巧克力"<%if(category.equals("巧克力:生巧克力")){%>
							selected
							<%}%>>生巧克力</option>
							<option value="巧克力:藝術巧克力"<%if(category.equals("巧克力:藝術巧克力")){%>
							selected
							<%}%>>藝術巧克力</option>
							<option value="巧克力:巴克巧克力"<%if(category.equals("巧克力:巴克巧克力")){%>
							selected
							<%}%>>巴克巧克力</option>
						</optgroup>
						<optgroup label="精緻小點">
							<option value="精緻小點:派 & 塔"<%if(category.equals("精緻小點:派 & 塔")){%>
							selected
							<%}%>>派 & 塔</option>
							<option value="精緻小點:泡芙"<%if(category.equals("精緻小點:泡芙")){%>
							selected
							<%}%>>泡芙</option>
							<option value="精緻小點:馬卡龍"<%if(category.equals("精緻小點:馬卡龍")){%>
							selected
							<%}%>>馬卡龍</option>
							<option value="精緻小點:可麗露"<%if(category.equals("精緻小點:可麗露")){%>
							selected
							<%}%>>可麗露</option>
							<option value="精緻小點:布蕾"<%if(category.equals("精緻小點:布蕾")){%>
							selected
							<%}%>>布蕾</option>
						</optgroup>
						<optgroup label="飲品">
							<option value="飲品:特調茶飲"<%if(category.equals("飲品:特調茶飲")){%>
							selected
							<%}%>>特調茶飲</option>
							<option value="飲品:香濃拿鐵"<%if(category.equals("飲品:香濃拿鐵")){%>
							selected
							 <%}%>>香濃拿鐵</option>
							<option value="飲品:經典咖啡"<%if(category.equals("飲品:經典咖啡")){%>
							selected
							<%}%>>經典咖啡</option>
							<option value="飲品:繽紛水果飲"<%if(category.equals("飲品:繽紛水果飲")){%>
							selected
							<%}%>>繽紛水果飲</option>
							<option value="飲品:其他"<%if(category.equals("飲品:其他")){%>
							selected
							<%}%>>其他</option>
						</optgroup>
					</select></td>
			</tr>
			<tr>
				<td>商品介紹:</td>
				<td><input type="TEXT" name="product_intro" size="45"
					value="<%=productBean.getProduct_intro()%>" /></td>
			</tr>
			<tr>
				<td>商品成份:</td>
				<td><input type="TEXT" name="product_ingredient" size="45"
					value="<%=productBean.getProduct_ingredient()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="product_price" size="45"
					value="<%=productBean.getProduct_price()%>" /></td>
			</tr>
			<tr>
				<td>現貨數量:</td>
				<td><input type="TEXT" name="product_available_qty" size="45"
					value="<%=productBean.getProduct_available_qty()%>" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td><select size="1" name="product_status">
					<% Integer product_status = productBean.getProduct_status(); 
						System.out.println(product_status);%>
					
				
					<option value="">請選擇上架狀態
						<option value=0 <%if(product_status == 0){%>
						selected
						<%}%>>未上架
						<option value=1 <%if(product_status == 1){%>
						selected
						<%}%>>上架中
				</select></td>
			</tr>
			<tr>
				<td>賞味天數:</td>
				<td><input type="TEXT" name="expiry_after_buying" size="45"
					value="<%=productBean.getExpiry_after_buying()%>" /></td>
			</tr>
			<tr>
				<td>商品熱量:</td>
				<td><input type="TEXT" name="product_calorie" size="45"
					value="<%=productBean.getProduct_calorie()%>" /></td>
			</tr>
			<tr>
				<td>商品甜度:</td>
				<td><input type="TEXT" name="degree_of_sweetness" size="45"
					value="<%=productBean.getDegree_of_sweetness()%>" /></td>
			</tr>
			<tr>
				<td>累計星等:</td>
				<td><input type="TEXT" name="total_star" size="45"
					value="<%=productBean.getTotal_star()%>" /></td>
			</tr>
			<tr>
				<td>累計評價次數:</td>
				<td><input type="TEXT" name="total_review" size="45"
					value="<%=productBean.getTotal_review()%>" /></td>
			</tr>
			<tr>
				<td>累計銷售:</td>
				<td><input type="TEXT" name="total_purchase" size="45"
					value="<%=productBean.getTotal_purchase()%>" /></td>
			</tr>
			
			<tr>
				<td>商品圖片：</td>
				<td>
					<input type="file" name="upfile1" accept="image/*">
					<div id="preview" class="picture_list"><span class="text">預覽圖</span></div>
                	<input type="image" src="/dessert_shop/product/backend_getPhoto?id=<%=productBean.getProduct_id()%>" name="my_img">
				</td>
			</tr>


<%--			這段的目的?? --%>
<%-- 			<jsp:useBean id="productSvc" scope="page" --%>
<%-- 				class="com.product.model.ProductService" /> --%>

		</table>
		<br><input type="hidden" name="action" value="update"> 
			<input type="hidden" name="product_id" value="<%=productBean.getProduct_id()%>"> 
			<input type="submit" value="送出修改">
	</FORM>
	
</body>

<script>
///////////照片的


</script>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- <link rel="stylesheet" type="text/css" -->
<%-- 	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- 
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style> 
-->
<!-- 
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=empVO.getHiredate()%>', // value:   new Date(), --%>
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

 -->


</html>