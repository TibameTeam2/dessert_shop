package com.notice.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class NoticeServlet extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {}

	/********************************* 取得全部通知(前臺) ********************************/
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

	/***************************** 新增時呼叫websocket(前臺) ***************************/
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

				NoticeWS.sendCustomizeMessage(noticeBean.getMember_account(), noticeBean.getNotice_content());

			} else {

				info.setFlag(false);
				info.setMsg("新增失敗");

			}
		}

		writeValueByWriter(res, info);
	}

	/******************************** 更新已讀 (前臺) ********************************/
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

	/*************************** 取得全部通知訊息(後臺) ********************************/
	public void backend_getAllNotice(HttpServletRequest req, HttpServletResponse res) {

		NoticeService noticeSvc = new NoticeService();
		List<NoticeBean> noticeList = noticeSvc.getAll();

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("取得所有通知訊息");
		info.setData(noticeList);

		writeValueByStream(res, info);

	}

	/*************************** 新增通知訊息並推播(後臺) ********************************/
	public void backend_addNotice(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		System.out.println("backend_addNotice 新增通知");

		Map<String, String[]> map = req.getParameterMap();
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

		NoticeBean noticeBean = new NoticeBean();
		try {
			BeanUtils.populate(noticeBean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		noticeBean.setRead_status(0);
		System.out.println(noticeBean);

		NoticeWS.sendCustomizeMessage(noticeBean.getMember_account(), noticeBean.getNotice_content());

		NoticeService noticeSvc = new NoticeService();
		boolean flag = noticeSvc.addNotice(noticeBean);

		ResultInfo info = new ResultInfo();
		if (flag) {
			info.setFlag(true);
			info.setMsg("新增成功!");
		} else {
			info.setFlag(false);
			info.setMsg("新增失敗!");
		}
		writeValueByWriter(res, info);
	}

	/*************************** 更新通知訊息並推播(後臺) ********************************/
	public void backend_update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Map<String, String[]> map = req.getParameterMap();
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

		NoticeBean noticeBean = new NoticeBean();

		System.out.println(noticeBean);

		try {
			BeanUtils.populate(noticeBean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		NoticeService noticeSvc = new NoticeService();
		boolean flag = noticeSvc.backend_updateNotice(noticeBean);

		ResultInfo info = new ResultInfo();

		if (flag) {
			info.setFlag(true);
			info.setMsg("更新成功!");
		} else {
			info.setFlag(false);
			info.setMsg("更新失敗!");
		}
		writeValueByWriter(res, info);
	}

	/*************************** 刪除通知訊息(後臺) ********************************/
	public void backend_delete(HttpServletRequest req, HttpServletResponse res) {

		Integer notice_id = new Integer(req.getParameter("notice_id"));

		ResultInfo info = new ResultInfo();

		try {
			NoticeService noticeSvc = new NoticeService();
			noticeSvc.deleteNotice(notice_id);
			info.setFlag(true);
			info.setMsg("已刪除通知!");
		} catch (Exception e) {
			info.setFlag(false);
			info.setMsg("刪除通知失敗!");
		}
		writeValueByWriter(res, info);

	}
}
