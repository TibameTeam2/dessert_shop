package com.order_master.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class OrderMasterServlet extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	
	
	public void getOrderMaster(HttpServletRequest req, HttpServletResponse res) {
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");

        
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else {
        	
        	
        	System.out.println("hello");
        	
        }
        
        
        
	}

	

	
}
