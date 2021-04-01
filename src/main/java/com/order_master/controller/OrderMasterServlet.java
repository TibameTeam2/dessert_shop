package com.order_master.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.order_master.model.OrderMasterBean;
import com.order_master.model.OrderMasterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class OrderMasterServlet extends BaseServlet {
	OrderMasterService OrderMasterSvc=new OrderMasterService();
	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	
	
	public void getOrderMaster(HttpServletRequest req, HttpServletResponse res) {
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        String code = req.getParameter("code");
        System.out.println(code);
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else {
        	
        	List<OrderMasterBean> list=OrderMasterSvc.getOrderMasterByMemberAccount(member.getMember_account());
        	info.setFlag(true);
        	info.setData(list);
        	info.setMsg(member.getMember_account()+" 的訂單資料");
        }
        writeValueByWriter(res, info);
        
        
	}

	

	
}
