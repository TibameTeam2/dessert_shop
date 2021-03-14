package com.member.controller;

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
        System.out.println("map= "+map);
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
        System.out.println(member);
        //調用service開始註冊

        boolean flag = service.register(member);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if(flag){
            //註冊成功
            info.setFlag(true);
            info.setMsg("註冊成功!");
            info.setRedirect(req.getContextPath()+"/index.html");
        }else{
            //註冊失敗
            info.setFlag(false);
            info.setRedirect(req.getContextPath()+"/index.html");
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
        System.out.println("map= "+map);
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
        Object member = req.getSession().getAttribute("member");

        //把member寫回前端
        ObjectMapper mapper = new ObjectMapper();
        res.setContentType("application/json;charset=utf-8");
        mapper.writeValue(res.getOutputStream(),member);
    }


    public String testForward(HttpServletRequest req, HttpServletResponse res){
        return "forward:/index.html";
    }

    public String testRedirect(HttpServletRequest req, HttpServletResponse res){
        return "redirect:/index.html";
    }

    public void testForward1(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/index.html").forward(req, res);
    }

    public void testRedirect1(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + "/index.html");
    }
}
