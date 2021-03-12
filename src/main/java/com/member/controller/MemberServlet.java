package com.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.util.BaseServlet;
import com.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class MemberServlet extends BaseServlet{


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
        //調用service開始註冊
        MemberService service = new MemberService();
        boolean flag = service.register(member);
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if(flag){
            //註冊成功
            info.setFlag(true);
            info.setErrorMsg("註冊成功!");
        }else{
            //註冊失敗
            info.setFlag(false);
            info.setErrorMsg("註冊失敗!");
        }

        //把info序列化為json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //將json寫回前端
        //设置content-type為json格式
        res.setContentType("application/json;charset=utf-8");
        res.getWriter().write(json);
    }
}
