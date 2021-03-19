package com.member.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.digest.DigestUtil;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MemberServlet extends BaseServlet {
    MemberService service = new MemberService();

    public void register(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("MemberServlet in register");
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + Convert.toStr(map));

        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);
        member.setMember_password(DigestUtil.md5Hex(member.getMember_password()));
        System.out.println(member);
        //調用service開始註冊

        boolean flag = service.register(member);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //註冊成功
            info.setFlag(true);
            info.setMsg("註冊成功!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
            //註冊失敗
            info.setFlag(false);
            info.setMsg("註冊失敗!");
        }
        writeValueByWriter(res, info);
    }

    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);
        member.setMember_password(DigestUtil.md5Hex(member.getMember_password()));

        member = service.login(member);

        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("帳號或密碼錯誤!");
        }

        if (member != null) {
            info.setFlag(true);
            req.getSession().setAttribute("member", member);//登入成功
            info.setMsg("登入成功!");
            info.setData(member);
            System.out.println("member = " + member);
        }
        writeValueByWriter(res, info);
    }

    public void isLogin(HttpServletRequest req, HttpServletResponse res) {
        //從session取得member
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else if (member != null) {
            info.setFlag(true);
            req.getSession().setAttribute("member", member);//登入成功
            info.setMsg("已登入!");
            info.setData(member);
            System.out.println("member = " + member);
        }
        writeValueByWriter(res, info);
    }

    public void emailActive(HttpServletRequest req, HttpServletResponse res) {

    }

    public void logout(HttpServletRequest req, HttpServletResponse res) {
        req.getSession().invalidate();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("已登出!");
        info.setRedirect(req.getContextPath() + "/index.html");
        System.out.println("info = " + toJson(info));
        writeValueByWriter(res, info);
    }


    public String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);
        try {
            /***************************1.接收請求參數****************************************/
            String member_account = req.getParameter("member_account");

            /***************************2.開始查詢資料****************************************/
            MemberService memberSvc = new MemberService();
            MemberBean member = memberSvc.getOneMember(member_account);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("member", member);         // 資料庫取出的empVO物件,存入req
//            String url = "/member/update_member_input.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
//            successView.forward(req, res);
            return "forwardSuccess:/member_jsp/update_member_input.jsp";

            /***************************其他可能的錯誤處理**********************************/
        } catch (Exception e) {
            errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/listAllEmp.jsp");
//            failureView.forward(req, res);
            return "forwardFail:/member_jsp/listAllEmp.jsp";
        }

    }


    public String backend_update(HttpServletRequest req, HttpServletResponse res)  {
        List<String> errorMsgs = new LinkedList<String>();
        // Store this set in the request scope, in case we need to
        // send the ErrorPage view.
        req.setAttribute("errorMsgs", errorMsgs);

        try {
            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String member_account = req.getParameter("member_account").trim();

            String member_name = req.getParameter("member_name");
            String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
            if (member_name == null || member_name.trim().length() == 0) {
                errorMsgs.add("員工姓名: 請勿空白");
            } else if(!member_name.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String member_phone = req.getParameter("member_phone").trim();
            if (member_phone == null || member_phone.trim().length() == 0) {
                errorMsgs.add("手機請勿空白");
            }

            String member_email = req.getParameter("member_email").trim();
            if (member_email == null || member_phone.trim().length() == 0) {
                errorMsgs.add("信箱請勿空白");
            }

            Integer member_gender = null;
            try {
                member_gender = new Integer(req.getParameter("member_gender").trim());
            } catch (NumberFormatException e) {
                member_gender = 0;
                errorMsgs.add("薪水請填數字.");
            }


            java.sql.Date member_birthday = null;
            try {
                member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
            } catch (IllegalArgumentException e) {
                member_birthday=new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入日期!");
            }

            MemberBean member = new MemberBean();
            member.setMember_account(member_account);
            member.setMember_name(member_name);
            member.setMember_phone(member_phone);
            member.setMember_email(member_email);
            member.setMember_gender(member_gender);
            member.setMember_birthday(member_birthday);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("member", member); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/emp/update_emp_input.jsp");
                failureView.forward(req, res);
                return null; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            System.out.println("before member = " + member);
            MemberService memberSve = new MemberService();
            memberSve.update(member);
            System.out.println("after member = " + member);
            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("member", member); // 資料庫update成功後,正確的的empVO物件,存入req
//            String url = "/emp/listOneEmp.jsp";
//            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//            successView.forward(req, res);
            return "/member_jsp/listOneEmp.jsp";
            /***************************其他可能的錯誤處理*************************************/
        } catch (Exception e) {
            errorMsgs.add("修改資料失敗:"+e.getMessage());
//            System.out.println(e.getMessage());
//            RequestDispatcher failureView = req
//                    .getRequestDispatcher("/emp/update_emp_input.jsp");
//            failureView.forward(req, res);
            return "/member_jsp/update_member_input.jsp";
        }
    }
}
