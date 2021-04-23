package com.subscriber_list.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notice.controller.NoticeWS;
import com.notice.model.NoticeService;
import com.subscriber_list.model.SubscriberListBean;
import com.subscriber_list.model.SubscriberListService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class SubscriberListServlet extends BaseServlet {

	SubscriberListService suberListSvc = new SubscriberListService();

	public void test(HttpServletRequest req, HttpServletResponse res) {
	}

	/*************************** 新增(前臺) ***************************/
	public void addSubscriberList(HttpServletRequest req, HttpServletResponse res) {

		String subscriber_email = req.getParameter("subscriber_email");

		SubscriberListBean subscriberListBean = new SubscriberListBean();
		subscriberListBean.setSubscriber_email(subscriber_email);

		ResultInfo info = new ResultInfo();
		Boolean flag = suberListSvc.addSSubscriberList(subscriberListBean);

		if (flag) {

			info.setFlag(true);
			info.setMsg("新增成功");

		} else {

			info.setFlag(false);
			info.setMsg("新增失敗");

		}
		writeValueByWriter(res, info);
	}

	/*************************** 取得全部訂閱(後臺) ***************************/
	public void backend_getAllSubscriberList(HttpServletRequest req, HttpServletResponse res) {

		List<SubscriberListBean> subscriberList = suberListSvc.getAll();

		ResultInfo info = new ResultInfo();

		info.setFlag(true);
		info.setMsg("取得訂閱清單");
		info.setData(subscriberList);

		writeValueByStream(res, info);
	}

	/*************************** 新增訂閱者(後臺) ***************************/
	public void backend_addSubscriberList(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		System.out.println("backend_addSubscriberList 新增訂閱者");

		Map<String, String[]> map = req.getParameterMap();
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

		SubscriberListBean subscriberListBean = new SubscriberListBean();
		try {
			BeanUtils.populate(subscriberListBean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		if (subscriberListBean.getMember_account().equals("x")) {
			subscriberListBean.setMember_account(null);
		}

		subscriberListBean.setSubscriber_status(1);
		System.out.println(subscriberListBean);

		boolean flag = suberListSvc.addSSubscriberList(subscriberListBean);

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

	/*************************** 更新訂閱資訊(後臺) ***************************/
	public void backend_update(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		Map<String, String[]> map = req.getParameterMap();
		System.out.println("map= " + new ObjectMapper().writeValueAsString(map));

		SubscriberListBean subscriberListBean = new SubscriberListBean();
		System.out.println(subscriberListBean);
		
	
		try {
			BeanUtils.populate(subscriberListBean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if (subscriberListBean.getMember_account().equals("x")) {
			subscriberListBean.setMember_account(null);
		}
		
		System.out.println(subscriberListBean);
		
		boolean flag = suberListSvc.backend_updateSubscriber(subscriberListBean);
		
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
	
	/*************************** 刪除訂閱資訊(後臺) ***************************/
	public void backend_delete(HttpServletRequest req, HttpServletResponse res) {
		
		Integer subscriber_id = new Integer(req.getParameter("subscriber_id"));
		
		ResultInfo info = new ResultInfo();

		try {
			
			suberListSvc.deleteSubscriberList(subscriber_id);
			info.setFlag(true);
			info.setMsg("已刪除通知!");
		} catch (Exception e) {
			info.setFlag(false);
			info.setMsg("刪除通知失敗!");
		}
		writeValueByWriter(res, info);

		
	}
		

	/*************************** 信箱重複驗證(後臺) ***************************/
	public void backend_checkEmail(HttpServletRequest req, HttpServletResponse res) {

		
		String subscriber_email = req.getParameter("subscriber_email");
		System.out.println("checkEmail=" + subscriber_email);

		SubscriberListBean subscriberListBean = suberListSvc.getSubscriberEmail(subscriber_email);

		ResultInfo info = new ResultInfo();
		if (subscriberListBean == null) {
			info.setFlag(true);
			info.setMsg("\"" + subscriber_email + "\"" + "可以使用");
		} else {
			info.setFlag(false);
			info.setMsg("信箱已使用，請確認再輸入");
		}
		writeValueByWriter(res, info);

	}

}
