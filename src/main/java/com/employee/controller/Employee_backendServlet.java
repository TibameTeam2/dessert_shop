package com.employee.controller;

import com.employee.model.EmployeeBean;
import com.employee.model.EmployeeService;
import com.util.BaseServlet;
import cn.hutool.core.codec.Base64;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.JDBCUtil;
import com.util.JedisUtil;
import com.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class Employee_backendServlet extends BaseServlet {
    EmployeeService empSvc = new EmployeeService();

    public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //獲取數據
        Map<String, String[]> map = req.getParameterMap();
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        //封裝物件
        EmployeeBean emp = new EmployeeBean();
        try {
            BeanUtils.populate(emp, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        emp.setEmployee_password(DigestUtil.md5Hex(emp.getEmployee_password()));
        emp = empSvc.login(emp);
        ResultInfo info = new ResultInfo();
        if (emp == null) {
            info.setFlag(false);
            info.setMsg("帳號或密碼錯誤!");
        } else if (emp != null) {
            info.setFlag(true);
            req.getSession().setAttribute("employee", emp);//登入成功
            info.setMsg("登入成功!");
            info.setData(emp);
            info.setRedirect(req.getContextPath() + "/TEA103G2/back-end/index.html");
        }
        writeValueByWriter(res, info);
    }

    public void isLogin(HttpServletRequest req, HttpServletResponse res) {
        //從session取得member
        EmployeeBean emp = (EmployeeBean) req.getSession().getAttribute("employee");
        ResultInfo info = new ResultInfo();
        if (emp == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else {
            info.setFlag(true);
            emp = empSvc.getOneEmp(emp.getEmployee_account());
            req.getSession().setAttribute("employee", emp);//登入成功
//            System.out.println(Base64.getEncoder().encodeToString(member.getMember_photo()));
            info.setMsg("已登入!");
            info.setData(emp);
//            System.out.println("member = " + member);
        }
        writeValueByWriter(res, info);
    }

    public void logout(HttpServletRequest req, HttpServletResponse res) {
        req.getSession().removeAttribute("employee");
        ;
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("後台已登出!");
        info.setRedirect(req.getContextPath() + "/TEA103G2/back-end/index.html");
//        System.out.println("info = " + toJson(info));
        writeValueByWriter(res, info);
    }


    public void backend_getAll(HttpServletRequest req, HttpServletResponse res) {
        List<EmployeeBean> list = empSvc.getAll();
        for (EmployeeBean emp : list) {
            emp.setEmployee_photo(null);
        }
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有員工資料!");
        info.setData(list);
        writeValueByWriter(res, info);
    }


    public void backend_getPhoto(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, SQLException, ClassNotFoundException {
        res.setContentType("image/png");
        Connection con = null;
        String driver = JDBCUtil.driver;
        String url = JDBCUtil.url;
        String userid = JDBCUtil.user;
        String passwd = JDBCUtil.password;
        ResultSet rs;
        Class.forName(driver);
        con = DriverManager.getConnection(url, userid, passwd);
        ServletOutputStream out = res.getOutputStream();
        Statement stmt = con.createStatement();
        String id = req.getParameter("id").trim();
//        ResultSet rs = stmt.executeQuery(
//                "SELECT member_photo FROM sweet.member WHERE member_account =" + id);

        PreparedStatement pstmt = null;
        pstmt = con.prepareStatement("SELECT employee_photo FROM sweet.employee WHERE employee_account =?");

        pstmt.setString(1, id);

        rs = pstmt.executeQuery();
        try {
            if (rs.next()) {
//            BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("member_photo"));
                IoUtil.write(res.getOutputStream(), true, IoUtil.readBytes(rs.getBinaryStream("employee_photo"), true));
            }
        } catch (Exception e) {
        } finally {
            rs.close();
            pstmt.close();
            con.close();
        }
    }

}
