<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.question_list.model.*" %>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%--<%--%>
<%--QuestionListService QLSvc = new QuestionListService();--%>
<%--List<QuestionListBean> list = QLSvc.getAll();--%>
<%--pageContext.setAttribute("list", list);--%>
<%--System.out.println(list);--%>
<%--%>--%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HelloTicket後臺</title>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/css/reset.css">
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/vendors/bootstrap/css/bootstrap.min.css">
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    <link rel="stylesheet"
          href="<%=request.getContextPath()%>/resources/css/back_template.css">
    <script
            src="<%=request.getContextPath()%>/resources/vendors/jquery/jquery-3.5.1.min.js"></script>
    <link
            href="<%=request.getContextPath()%>/resources/vendors/daterangepicker/css/daterangepicker.css"
            rel="stylesheet">
    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
    <style>
        #sidebar {
            overflow: scroll;
        }

        ::-webkit-scrollbar {
            width: 15px;
        }

        /* 这是针对缺省样式 (必须的) */
        ::-webkit-scrollbar-track {
            background-color: #b46868;
        }

        /* 滚动条的滑轨背景颜色 */
        ::-webkit-scrollbar-thumb {
            background-color: rgba(0, 0, 0, 0.2);
        }

        /* 滑块颜色 */
        ::-webkit-scrollbar-button {
            background-color: #7c2929;
        }

        /* 滑轨两头的监听按钮颜色 */
        ::-webkit-scrollbar-corner {
            background-color: black;
        }

        /* 横向滚动条和纵向滚动条相交处尖角的颜色 */

        /* 內容 */
        p {
            color: black;
        }

        .middle_bar {
            background-color: #564163;
            margin-top: 50px;
            margin-bottom: 30px;
        }

        .middle_bar > p {
            color: white;
            text-align: left;
            margin-left: 30px;
            padding-top: 20px;
            padding-bottom: 20px;
        }

        /* searching */
        .search {
            width: 30%;
            margin-bottom: 20px;
            margin-top: 20px;
            height: 50px;
            background-color: #fff;
            padding: 10px;
            border-radius: 5px;
            z-index: 100;
            position: relative;
        }

        .search-input {
            color: white;
            border: 0;
            outline: 0;
            background: none;
            width: 0;
            margin-top: 5px;
            caret-color: transparent;
            line-height: 20px;
            transition: width 0.4s linear;
        }

        .search .search-input {
            padding: 0 10px;
            width: 100%;
            caret-color: #536bf6;
            font-size: 19px;
            font-weight: 300;
            color: black;
            transition: width 0.4s linear
        }

        .search-icon {
            height: 34px;
            width: 34px;
            float: right;
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            background-color: #564163;
            font-size: 10px;
            bottom: 30px;
            position: relative;
            border-radius: 5px
        }

        .search-icon:hover {
            color: #fff !important
        }

        .col-5 {
            text-align: left;
            padding-left: 30px;
        }

        .main_content_part > p {
            color: black;
            font-size: 20px;
        }

        .d-flex {
            text-align: center;
            margin-left: 50px;
        }

        .d-flex > p {
            margin: auto 0px;
            color: white;
            margin-right: 20px;
        }

        .btn_group {
            /* position: relative; */

        }

        .btn {
            color: white !important;
            background-color: #564163 !important;
            border-radius: 20px !important;
            position: absolute;
        }

        table {
            border: 1px solid #000;
            font-family: 微軟正黑體;
            font-size: 16px;
            width: 90%;
            border: 1px solid #000;
            text-align: center;
            border-collapse: collapse;
            margin-top: 130px;
        }

        th {
            background-color: #71619D;
            padding: 10px;
            border: 1px solid #000;
            color: #fff;
        }

        td {
            border: 1px solid #000;
            padding: 10px;
        }

        /* .fixed-table-container{
          padding-top: 70px !important;
          } */
        /* toggle checkbox */
        .switch {
            position: relative;
            display: inline-block;
            width: 50px;
            /* height: 20px; */
        }

        .switch input {
            opacity: 0;
            width: 0;
            height: 0;
        }

        .slider {
            position: absolute;
            cursor: pointer;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #ccc;
            -webkit-transition: .4s;
            transition: .4s;
        }

        .slider:before {
            position: absolute;
            content: "";
            height: 22px;
            width: 22px;
            left: 4px;
            background-color: #71619D;
            -webkit-transition: .4s;
            transition: .4s;
        }

        input:checked + .slider {
            background-color: #2196F3;
        }

        input:focus + .slider {
            box-shadow: 0 0 1px #2196F3;
        }

        input:checked + .slider:before {
            -webkit-transform: translateX(26px);
            -ms-transform: translateX(26px);
            transform: translateX(26px);
        }

        /* Rounded sliders */
        .slider.round {
            border-radius: 34px;
        }

        .slider.round:before {
            border-radius: 50%;
        }

        .fixed-table-body {
            margin-top: -100px !important;
            margin-bottom: -80px !important;
        }

        .qlist_num {
            display: none;
        }
    </style>
    <!-- TinyMCE v4.7.6 -->
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/tinymce/4.7.6/tinymce.min.js"></script>

    <!-- table sort -->


    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.css">
    <!--  -->
</head>

<body>
<%--	<%@ include file="/includes/backEnd/sidebar.html"%>--%>


<!-- Page Content  -->
<div id="content">
    <!-- 右上按鈕 -->
    <div
            style="width: 80px; background-color: #564163; border-radius: 10%; color: #ffffff; line-height: 30px;">小遊戲列表
    </div>

    <div class="container-fluid">
        <button type="button" id="sidebarCollapse" class="navbar-btn">
            <span></span> <span></span> <span></span>
        </button>
    </div>
    <!-- 右上按鈕 -->

    <!-- 內容 -->
    <div class="main_zone">


        <div class="middle_bar">

            <div class="d-flex"
            ">
            <p>篩選</p>
            <div class="search">
                <input type="text" class="search-input" placeholder="Search..."
                       name=""> <a href="#" class="search-icon"> <i
                    class="fa fa-search"></i>
            </a>
            </div>
        </div>
    </div>

    <!-- 中間 table -->
    <div class="col-12" class="btn_group">
        <div class="container">
            <div class="row">
                <div class="col-12">

                    <!-- table -->

                    <table id="sort-table" data-toggle="table">
                        <thead>
                        <tr>

                            <th data-field="select_box"><input type="checkbox"
                                                               id="all">選取
                            </th>
                            <th data-field="qlist_numm" class="qlist_num">編號</th>
                            <th data-field="qlist_name" data-sortable="true">題庫名</th>
                            <th data-field="qlist_status" data-sortable="true">上架狀態</th>
                            <th data-field="lupdatedee" data-sortable="true">最後編輯員工</th>
                            <th data-field="edit">編輯</th>
                        </tr>
                        </thead>

                        <!-- 								<tr game_qlist_id="1"> -->
                        <!-- 									<td><input type="checkbox" class="chk"></td> -->
                        <!-- 									<td class="qlist_num">1</td> -->
                        <!-- 									<td>一月題目庫</td> -->
                        <!-- 									<td style="padding: auto;"><label class="switch"> -->
                        <!-- 											<input type="checkbox"> <span class="slider round"></span> -->
                        <!-- 									</label></td> -->
                        <!-- 									<td>囂張老大</td> -->
                        <!-- 									<td><a href="#"><img class="icon" -->
                        <!-- 											src="img\edit_icon.png" alt=""></a></td> -->
                        <!-- 								</tr> -->


                        <tr game_qlist_id="1">
                            <td><input type="checkbox" class="chk"></td>
                            <td class="qlist_num">1</td>
                            <td>1</td>
                            <td style="padding: auto;"><label class="switch">
                                <input type="checkbox"> <span class="slider round"></span>
                            </label></td>

                            <!-- 										<td style="padding: auto;"><label class="switch"> -->
                            <!-- 												<input type="checkbox"  checked> <span class="slider round"></span> -->
                            <!-- 										</label></td> -->
                            <td>123</td>
                            <%--
                            									<FORM METHOD="post" ACTION="/test/test" id="revise_game_list">--%>
                            <td>
                            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do"
                                  style="margin-bottom: 0px;">

                                    <img class="icon"
                                         src="<%=request.getContextPath()%>\resources\img\edit_icon.png" alt="">
                                    <input type="submit" value="刪除">
                                    <input type="hidden" name="game_qlist_id" value="123123">
                                    <input type="hidden" name="action" value="getOne_For_Update">

                            </FORM>
                            </td>
                            <%--										</FORM>--%>
                        </tr>


                    </table>


                    <div>
                        <button type="submit" class="btn btn_add" style="right: 210px">新增</button>
                        <button type="submit" class="btn btn_delete"
                                style="right: 110px">刪除
                        </button>
                        <button type="submit" class="btn btn_save" style="right: 10px">儲存</button>
                    </div>


                </div>
            </div>
        </div>
        <!-- main_zone end-->


    </div>
    <!-- table sort -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <script
            src="https://unpkg.com/bootstrap-table@1.16.0/dist/bootstrap-table.min.js"></script>
    <!--  -->
    <script src="vendors\calendar_selector\the-datepicker.js"></script>
    <script src="./vendors/popper/popper.min.js"></script>
    <script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // 收縮視窗
            // $("#sidebar").mCustomScrollbar({
            //     theme:"dark"
            // });
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar, #content').toggleClass('active');
                $(this).toggleClass('active');
                $('.collapse.in').toggleClass('in');
                $('a[aria-expanded=true]').attr('aria-expanded', 'false');
            });

            <!-- all select -->
            $("#all").on("click", function () {
                if ($(this).prop("checked")) {
                    $(".chk").prop("checked", true);
                } else {
                    $(".chk").prop("checked", false);
                }
            });

        });
        //送資料
        $("img").click(function () {
            console.log("123");
            $("#revise_game_list").submit();
        });


    </script>
</body>

</html>
