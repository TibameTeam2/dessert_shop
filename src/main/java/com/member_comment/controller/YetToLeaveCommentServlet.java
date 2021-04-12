package com.member_comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.member_comment.model.YetToLeaveCommentBean;
import com.member_comment.model.YetToLeaveCommentService;
import com.order_master.model.HistoryCommentOrderMasterBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class YetToLeaveCommentServlet extends BaseServlet {

	YetToLeaveCommentService yetToLeaveCommentSvc = new YetToLeaveCommentService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	//取得尚未評價的order_master資料
	public void getYetToLeaveComment(HttpServletRequest req, HttpServletResponse res) {
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
			info.setFlag(false);
			info.setMsg("尚未登入!");
		} else {
			String memberAccount = member.getMember_account();
			List<YetToLeaveCommentBean> ytlcBlist = yetToLeaveCommentSvc.findByMemberAccount(memberAccount);
			info.setFlag(true);
			info.setData(ytlcBlist);
			info.setMsg(member.getMember_account() + " 的尚未評價資料");
		}
		writeValueByWriter(res, info);
	}

	//取得尚未評價的order_detail資料
	public void getYetToLeaveComment1(HttpServletRequest req, HttpServletResponse res) {

		String order_master_id = req.getParameter("orderMasterId");
		System.out.println("orderMasterId = " + order_master_id);

		List<YetToLeaveCommentBean> ytlcBlist = yetToLeaveCommentSvc
				.findByOrderMasterId(Convert.toInt(order_master_id));
		System.out.println("List<YetToLeaveCommentBean> ytlcBlist = " + ytlcBlist);

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(ytlcBlist);
		info.setMsg("orderMasterId = " + order_master_id + "    的資料");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端
	}

}
