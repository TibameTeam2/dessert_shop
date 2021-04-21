package com.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BackendLoginFilter implements Filter {

    private FilterConfig config;

    public void init(FilterConfig config) {
        this.config = config;
    }

    public void destroy() {
        config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Object member = session.getAttribute("employee");
        if (member == null) {
            session.setAttribute("location", req.getRequestURI());
            res.sendRedirect(req.getContextPath() + "/TEA103G2/back-end/login.html");
            return;
        } else {
            chain.doFilter(request, response);
        }
    }
}