package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 透過映射的方式，做方法的分發，類似controller
 */

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //準備方法的分發
        //獲取請求路徑
        String uri = req.getRequestURI();
        System.out.print("請求的uri:" + uri);//  /dessert_shop/member/register
        //字串切割後，獲取方法名稱
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("  方法名稱:" + methodName);//  register
        //誰調用的?
        System.out.println("This:"+this);//com.member.controller.MemberServlet@438d0d75
        try {
            //獲取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //執行方法
            //暴力映射(連private都可以調用)
            //method.setAccessible(true);
            String dispatcherPage = (String)method.invoke(this, req, res);
            //如果有回傳字串，幫你轉送，格式forward:/index.html或redirect:/index.html
            //如果只傳/index.html預設使用forward
            if (dispatcherPage==null)return;
            System.out.println("dispatcherPage = " + dispatcherPage);
            String page;
            if(dispatcherPage.contains(":")){
                String[] data = dispatcherPage.split(":");
                System.out.println("分號前文字:"+data[0] + " 分號後文字:"+data[1]);
                page = data[1];
                if(data[0].contains("forward")){
                    req.getRequestDispatcher(page).forward(req,res);
                }else if(data[0].contains("redirect")){
                    res.sendRedirect(req.getContextPath() + page);
                }
            }
            else{
                // 如果沒有寫forward:或redirect: 則預設使用forward
                page = dispatcherPage;
                System.out.println("page:"+page);
                if (page == null || page.equals("")){
                    System.out.println("page為空 "+page);
//                    res.sendRedirect(req.getContextPath() +"/error.jsp");
                }else {
                    System.out.println("預設forward:"+page);
                    req.getRequestDispatcher(page).forward(req, res);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 將傳入的物件序列化成json，用字節流(byte)的方式寫回前端
     */
    public void writeValueByStream(HttpServletResponse response,Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getOutputStream(), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 將傳入的物件序列化成json，用字符流(char)的方式寫回前端
     */
    public void writeValueByWriter(HttpServletResponse response,Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        try {
            mapper.writeValue(response.getWriter(), obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 將物件序列化為json，並返回
     */
    public String toJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



    // 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
    public String getFileNameFromPart(Part part) {
        String header = part.getHeader("content-disposition");
        System.out.println("header=" + header); // 測試用
        String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
        System.out.println("filename=" + filename); // 測試用
        if (filename.length() == 0) {
            return null;
        }
        return filename;
    }
}
