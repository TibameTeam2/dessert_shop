package com.order_master.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.order_master.model.HistoryCommentOrderMasterBean;
import com.order_master.model.HistoryCommentOrderMasterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class HistoryCommentOrderMasterServlet extends BaseServlet {

	HistoryCommentOrderMasterService historyCommentOrderMasterSvc = new HistoryCommentOrderMasterService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	
	public void getHistoryCommentOrderMaster(HttpServletRequest req, HttpServletResponse res){
    	MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
        } else {
        	String member_account = member.getMember_account();
        	List<HistoryCommentOrderMasterBean> list = historyCommentOrderMasterSvc.getOrderMasterByMemberAccount(member_account);
        	info.setFlag(true);
        	info.setData(list);
        	info.setMsg(member.getMember_account()+" 的訂單資料");
        }
        writeValueByWriter(res, info);

    }
}