package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解決全站亂碼，過濾所有請求
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //父介面轉子介面
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;

        //排除css js檔，這兩種如果被加上text/html;charset=UTF-8 會錯誤
        String request_uri = request.getRequestURI();
        if(request_uri.contains(".css") || request_uri.contains(".js")||request_uri.contains(".html")||request_uri.contains(".jpg")){
            //如果發現是css或者js檔案，直接放行
            filterChain.doFilter(request,response);
            return;
        }


        //獲取請求方法
        String method = request.getMethod();
        //解決post中文亂碼問題
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("UTF-8");
        }
        //處理響應的亂碼
        response.setContentType("text/html;charset=UTF-8");
//        response.setContentType("text/html;charset=BIG5");
//        response.setContentType("text/html;charset=iso-8859-1");

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
