package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 透過映射的方式，做方法的分發，類似controller
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //System.out.println("baseServlet的service方法被执行了...");
        //準備方法的分發
        //獲取請求路徑
        String uri = req.getRequestURI();
        System.out.println("請求的uri:" + uri);//  /dessert_shop/member/register
        //字串切割後，獲取方法名稱
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("方法名稱：" + methodName);
        //誰調用的?
        System.out.println(this);//com.member.controller.RegisterMemberServlet@438d0d75
        try {
            //獲取方法
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //執行方法
            //暴力映射(連private都可以調用)
            //method.setAccessible(true);
            String dispatcherPage = (String)method.invoke(this, req, res);

//            RequestDispatcher dispatcher = req.getRequestDispatcher("/forwarding.jsp");
//            dispatcher.forward(req, res);
            String page;
            if (dispatcherPage==null)return;
            if(dispatcherPage.contains(":")){
                String[] data = dispatcherPage.split(":");
                System.out.println("分號前文字:"+data[0] + " 分號後文字:"+data[1]);
                page = data[1];
                if("forward".equals(data[0])){
                    req.getRequestDispatcher(page).forward(req,res);
                }else if("redirect".equals(data[0])){
                    res.sendRedirect(req.getContextPath() + page);
                }
            }
//            else{
//                // 如果沒有寫forward:或redirect: 則預設使用forward
//                page = dispatcherPage;
//                System.out.println("page:"+page);
//                if (page == null || page == ""){
//                    System.out.println("page 為空");
////                    res.sendRedirect(req.getContextPath() +"/500.jsp");
//                }else {
//                    req.getRequestDispatcher(page).forward(req, res);
//                }
//            }



        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 將傳入的物件序列化成json，直接寫回前端
     *
     * @param obj
     */
    public void writeValue(Object obj, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), obj);
    }

    /**
     * 將物件序列化為json，並返回
     *
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
