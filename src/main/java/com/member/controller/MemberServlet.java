package com.member.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.digest.DigestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MemberServlet extends BaseServlet{
    MemberService service = new MemberService();

    public void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("MemberServlet in register");
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= "+ new ObjectMapper().writeValueAsString(map));

        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member,map);
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
        if(flag){
            //註冊成功
            info.setFlag(true);
            info.setMsg("註冊成功!");
            info.setRedirect(req.getContextPath()+"/login.html");
        }else{
            //註冊失敗
            info.setFlag(false);
            info.setRedirect(req.getContextPath()+"/login.html");
            info.setMsg("註冊失敗!");
        }

        //把info序列化為json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //將json寫回前端
        //设置content-type為json格式
        res.setContentType("application/json;charset=utf-8");
        res.getWriter().write(json);

//        res.sendRedirect(req.getContextPath()+"/index.html");
    }


    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= "+ new ObjectMapper().writeValueAsString(map));
        //封裝物件
        MemberBean member = new MemberBean();
        try {
            BeanUtils.populate(member,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(member);
        member.setMember_password(DigestUtil.md5Hex(member.getMember_password()));

        member = service.login(member);

        ResultInfo info = new ResultInfo();
        if(member==null){
            info.setFlag(false);
            info.setMsg("帳號或密碼錯誤!");
        }

        if(member!=null){
            info.setFlag(true);
            req.getSession().setAttribute("member",member);//登入成功
            info.setMsg("登入成功!");
            info.setData(member);
            System.out.println("member = " + member);
        }


        //把info序列化為json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        System.out.println(json);
        //將json寫回前端
        //设置content-type為json格式
        res.setContentType("application/json;charset=utf-8");
        res.getWriter().write(json);

    }


    public void isLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //從session取得member
        MemberBean member = (MemberBean)req.getSession().getAttribute("member");

        ResultInfo info = new ResultInfo();
        if(member==null){
            info.setFlag(false);
            info.setMsg("尚未登入!");
        }

        else if(member!=null){
            info.setFlag(true);
            req.getSession().setAttribute("member",member);//登入成功
            info.setMsg("已登入!");
            info.setData(member);
            System.out.println("member = " + member);
        }




        writeValueByWriter(res,info);
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(member);
//        System.out.println("json = " + json);
//        res.setContentType("application/json;charset=utf-8");
//        res.getWriter().write(json);
    }

    public void emailActive(HttpServletRequest req, HttpServletResponse res){
        
    }


    //測試Forward
    public String testForward1(HttpServletRequest req, HttpServletResponse res){
        return "forward:/index.jsp";
    }
    public String testForward2(HttpServletRequest req, HttpServletResponse res){
        return "/index.jsp";
    }
    public void testForward3(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
    //不可使用此種
    public String testForward4(HttpServletRequest req, HttpServletResponse res){
        return "index.jsp";
    }

    //測試Redirect
    public String testRedirect1(HttpServletRequest req, HttpServletResponse res){
        return "redirect:/index.html";
    }
    public void testRedirect2(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + "/index.html");
    }

    //測試回傳
    public String testReturn1(HttpServletRequest req, HttpServletResponse res){
        return "";
    }
    public String testReturn2(HttpServletRequest req, HttpServletResponse res){
        return null;
    }
    public void testReturn3(HttpServletRequest req, HttpServletResponse res){
    }
}
