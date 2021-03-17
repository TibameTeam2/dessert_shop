package com.member.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
import cn.hutool.crypto.digest.DigestUtil;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberServlet extends BaseServlet{
    MemberService service = new MemberService();

    public void register(HttpServletRequest req, HttpServletResponse res){
        System.out.println("MemberServlet in register");
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= "+ Convert.toStr(map));

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
            info.setMsg("註冊失敗!");
        }
        writeValueByWriter(res,info);
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
        writeValueByWriter(res,info);
    }

    public void isLogin(HttpServletRequest req, HttpServletResponse res){
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
    }

    public void emailActive(HttpServletRequest req, HttpServletResponse res){

    }

    public void logout(HttpServletRequest req, HttpServletResponse res){
        req.getSession().invalidate();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("已登出!");
        info.setRedirect(req.getContextPath()+"/index.html");
        System.out.println("info = " + toJson(info));
        writeValueByWriter(res,info);
    }

    public void json(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        取得request傳進來的字串
        String body = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        System.out.println("body = " + body);
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        ResultInfo Info = mapper.readValue(body,ResultInfo.class);
        System.out.println("Info = " + Info);
//        把字串轉成Map格式
        Map<String, Object> map = mapper.readValue(body, new TypeReference<Map<String, Object>>() {});
        System.out.println(map.get("123"));
        Object temp = map.get("123");
//        把字串自動封裝成Bean
        MemberBean member = mapper.readValue(mapper.writeValueAsString(temp), MemberBean.class);
        System.out.println("member = " + member);
    }

    public void gson(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String body = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        System.out.println("body = " + body);
        Gson gson = new Gson();
        MemberBean member = gson.fromJson(body,MemberBean.class);
        System.out.println("member = " + member);
    }



    //測試Forward
    public String testForward1(HttpServletRequest req, HttpServletResponse res){
        return "forward:/index.jsp";
    }
    public String testForward2(HttpServletRequest req, HttpServletResponse res){
        return "/index.html";
    }
    public String testForward3(HttpServletRequest req, HttpServletResponse res){
        return "forwardSuccess:/index.jsp";
    }
    public void testForward4(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
    //不可使用此種
    public String testForward5(HttpServletRequest req, HttpServletResponse res){
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
