package com.notice.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class NoticeServlet extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {
	}

	/********************************* 取得全部通知  ********************************/
	public void getAllNotice(HttpServletRequest req, HttpServletResponse res) {

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		NoticeService noticeSvc = new NoticeService();

		ResultInfo info = new ResultInfo();
		if (member != null) {

			info.setFlag(true);
			List<NoticeBean> notice = noticeSvc.getMember(member.getMember_account());

			info.setMsg("通知訊息");
			info.setData(notice);

//			System.out.println("notice = " + notice);
//			System.out.println("member = " + member);
		}

		writeValueByWriter(res, info);

	}

	/********************************* 新增時呼叫websocket ********************************/
	public void addNotice(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {

		// 請求參數:類型、內容、會員帳號
		Integer noticeType = new Integer(req.getParameter("notice_type"));
		String noticeContent = new String(req.getParameter("notice_content").getBytes("ISO-8859-1"), "UTF-8");
		String memberAccount = req.getParameter("member_account");
		String noticeDispatcher = req.getParameter("notice_dispatcher");

		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setNotice_type(noticeType);
		noticeBean.setNotice_content(noticeContent);
		noticeBean.setRead_status(0);
		noticeBean.setMember_account(memberAccount);
		noticeBean.setNotice_dispatcher(noticeDispatcher);

		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		NoticeService noticeSvc = new NoticeService();

		ResultInfo info = new ResultInfo();

		if (member != null) {

			Boolean flag = noticeSvc.addNotice(noticeBean);

			if (flag) {

				info.setFlag(true);
				info.setMsg("新增成功");

				NoticeWS.sendCustomizeMessage(member.getMember_account(), noticeBean.getNotice_content());

			} else {

				info.setFlag(false);
				info.setMsg("新增失敗");

			}
		}

		writeValueByWriter(res, info);
	}

	/************************************ 更新已讀 ************************************/
	public void alreadyReadNotice(HttpServletRequest req, HttpServletResponse res) {


		Integer notice_id = new Integer(req.getParameter("notice_id"));
		String memberAccount = req.getParameter("member_account");

		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setNotice_id(notice_id);
		noticeBean.setMember_account(memberAccount);
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		NoticeService noticeSvc = new NoticeService();
		
		ResultInfo info = new ResultInfo();
		
		if (member != null) {
			
			Boolean flag = noticeSvc.updateRead(noticeBean);

			if (flag) {

				info.setFlag(true);
				info.setMsg("已將id:" + notice_id + "更新狀態");
				

			} else {

				info.setFlag(false);
				info.setMsg("更新失敗");
			}
		}
		writeValueByWriter(res, info);
	}

}
