package com.notice.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.member.model.MemberService;
import com.mysql.cj.protocol.x.Notice;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.order_master.model.OrderMasterBean;
import com.order_master.model.OrderMasterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class NoticeServlet extends BaseServlet {
	
	
	
	public void test(HttpServletRequest req,HttpServletResponse res) {}
	

	public void Msg(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
//		MemberService memberSvc = new MemberService();
		NoticeService noticeSvc = new NoticeService();

//		member = memberSvc.login(member);
		ResultInfo info = new ResultInfo();

		if (member != null) {
			
			info.setFlag(true);
			req.getSession().setAttribute("member", member);// 登入成功
			List<NoticeBean> notice = noticeSvc.getMember(member.getMember_account());

			info.setMsg("登入成功!");
			info.setData(notice);

			System.out.println("notice = " + notice);
			System.out.println("member = " + member);
		}

		writeValueByWriter(res, info);

	}
	
	public void addMsg(HttpServletRequest req,HttpServletResponse res) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		//請求參數:類型、內容、會員帳號
		Integer noticeType = new Integer(req.getParameter("notice_type"));
		String noticeContent = req.getParameter("notice_content");
		String memberAccount =req.getParameter("member_account");
		
//		java.sql.Timestamp noticeTime = null;
//		try {
//			noticeTime = java.sql.Timestamp.valueOf(req.getParameter("noticeTime".trim()));
//		} catch (IllegalArgumentException e) {
//			noticeTime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入日期!");
//		}

		
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setNotice_type(noticeType);
		noticeBean.setNotice_content(noticeContent);
//		noticeBean.setNotice_time(noticeTime);
		noticeBean.setRead_status(0);
		noticeBean.setMember_account(memberAccount);
		
		
		System.out.println();
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		MemberService memberSvc = new MemberService();
		OrderMasterService orderSvc = new OrderMasterService();

		member = memberSvc.login(member);
		ResultInfo info = new ResultInfo();

		if (member != null) {
			
			
			info.setFlag(true);
			req.getSession().setAttribute("member", member);// 登入成功
			OrderMasterBean order = orderSvc.getOrderMaster(member.getMember_account());

			info.setMsg("登入成功!");
			info.setData(order);

			System.out.println("order = " + order);
			System.out.println("member = " + member);
		}
	
//		1.購買成功:時間、金額(會員帳號、會員名稱)
//		2.取貨提醒:時間、金額(會員帳號、會員名稱)

//		3.訂位成功:時間、人數(會員帳號、會員名稱)
//		4.訂位提醒:時間、人數(會員帳號、會員名稱)

//		5.取消訂位成功:時間(會員帳號、會員名稱)
		
		writeValueByWriter(res, info);
	}

}
