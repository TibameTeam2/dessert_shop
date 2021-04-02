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

	public void test(HttpServletRequest req, HttpServletResponse res) {
	}

	public void Msg(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
<<<<<<< HEAD
		NoticeService noticeSvc = new NoticeService();

=======
//		MemberService memberSvc = new MemberService();
		NoticeService noticeSvc = new NoticeService();

//		member = memberSvc.login(member);
>>>>>>> master
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

	//自動新增的API
	public void addMsg(HttpServletRequest req, HttpServletResponse res) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		// 請求參數:類型、內容、會員帳號
		Integer noticeType = new Integer(req.getParameter("notice_type"));
		String noticeContent = req.getParameter("notice_content");
		String memberAccount = req.getParameter("member_account");

		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setNotice_type(noticeType);
		noticeBean.setNotice_content(noticeContent);
		noticeBean.setRead_status(0);
		noticeBean.setMember_account(memberAccount);

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		NoticeService noticeSvc = new NoticeService();

		ResultInfo info = new ResultInfo();

		if (member != null) {

			Boolean flag = noticeSvc.addNotice(noticeBean);

			if (flag) {

				info.setFlag(true);
				info.setMsg("新增成功");

			} else {

				info.setFlag(false);
				info.setMsg("新增失敗");

			}
		}

		writeValueByWriter(res, info);
	}

}
