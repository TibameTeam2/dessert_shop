package com.util;

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

public class ServletTest extends BaseServlet {

    public void json(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        取得request傳進來的字串
        String body = IOUtils.toString(req.getInputStream(), StandardCharsets.UTF_8);
        System.out.println("body = " + body);
        ObjectMapper mapper = new ObjectMapper();
        ResultInfo resultInfo = new ResultInfo();
        ResultInfo Info = mapper.readValue(body, ResultInfo.class);
        System.out.println("Info = " + Info);
//        把字串轉成Map格式
        Map<String, Object> map = mapper.readValue(body, new TypeReference<Map<String, Object>>() {
        });
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
        MemberBean member = gson.fromJson(body, MemberBean.class);
        System.out.println("member = " + member);
    }


    //測試Forward
    public String testForward1(HttpServletRequest req, HttpServletResponse res) {
        return "forward:/index.jsp";
    }

    public String testForward2(HttpServletRequest req, HttpServletResponse res) {
        return "/index.html";
    }

    public String testForward3(HttpServletRequest req, HttpServletResponse res) {
        return "forwardSuccess:/index.jsp";
    }

    public void testForward4(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }

    //不可使用此種
    public String testForward5(HttpServletRequest req, HttpServletResponse res) {
        return "index.jsp";
    }

    //測試Redirect
    public String testRedirect1(HttpServletRequest req, HttpServletResponse res) {
        return "redirect:/index.html";
    }

    public void testRedirect2(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendRedirect(req.getContextPath() + "/index.html");
    }

    //測試回傳
    public String testReturn1(HttpServletRequest req, HttpServletResponse res) {
        return "";
    }

    public String testReturn2(HttpServletRequest req, HttpServletResponse res) {
        return null;
    }

    public void testReturn3(HttpServletRequest req, HttpServletResponse res) {
    }
}
