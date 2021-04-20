package com.dealer_reply.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dealer_reply.model.DealerReplyBean;
import com.dealer_reply.model.DealerReplyService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class DealerReplyServlet extends BaseServlet {
	
	//用在新增業者回覆
	public void addDealerReply(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		DealerReplyService dealerReplySvc = new DealerReplyService();
		
		Integer review_id = Convert.toInt(req.getParameter("ReviewId"));
		String reply_content = req.getParameter("ReplyContent");
		String employee_account = req.getParameter("EmployeeAccount");
		
		System.out.println(review_id);
		System.out.println(reply_content);
		System.out.println(employee_account);
		
		Integer reply_id = dealerReplySvc.addDealerReply(review_id, reply_content, employee_account);
		System.out.println(reply_id);
		
		ResultInfo info = new ResultInfo();
	
		if(reply_id != 0) {
			info.setFlag(true);
			info.setMsg("成功新增回覆");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/page-list-member-comment.html");
			System.out.println("成功的跳轉");
		} else {
			info.setFlag(false);
			info.setMsg("新增回覆失敗");
			info.setRedirect(req.getContextPath() + req.getServletPath() + "/page-list-member-comment.html");
			System.out.println("失敗的跳轉");
		}
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

		
	}
	
	//用在更新業者回覆
	public void updateDealerReply(HttpServletRequest req, HttpServletResponse res) {
		
		DealerReplyService dealerReplySvc = new DealerReplyService();
		
		Integer review_id = Convert.toInt(req.getParameter("ReviewId"));
		String reply_content = req.getParameter("ReplyContent");
		Timestamp reply_time = java.sql.Timestamp.valueOf(req.getParameter("NewReplyTime")); 
		String employee_account = req.getParameter("EmployeeAccount");
		
		System.out.println(review_id);
		System.out.println(reply_content);
		System.out.println(reply_time);
		System.out.println(employee_account);
		
		DealerReplyBean dealerReplyBean = dealerReplySvc.updateDealerReply(review_id, reply_content, reply_time, employee_account);
		
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(dealerReplyBean);
		info.setMsg("成功更新回覆");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
	}
}
