package com.order_detail.controller;

import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_detail.model.OrderDetailBean;
import com.order_detail.model.OrderDetailService;
import com.order_master.model.HistoryCommentOrderMasterBean;
import com.order_master.model.OrderMasterBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class OrderDetailServlet extends BaseServlet {

	OrderDetailService orderDetailSvc = new OrderDetailService();
	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}
	
	/*
	 * 在HistoryCommentOrderMasterServlet確認member為已登入且member_account='amy'是有order_master_id
	 * (且是撈得到不只一筆order_master_id)
	 * 
	 * 接著要在這支OrderDetailServlet裡列出每個order_master_id所對應的多個order_detail_id
	 * 
	 */
	
	public void getHistoryCommentOrderDetail(HttpServletRequest req, HttpServletResponse res) {
		
		String orderMasterId = req.getParameter("orderMasterId");
		System.out.println("orderMasterId = " + orderMasterId);
		
		OrderDetailService service = new OrderDetailService();
		List<OrderDetailBean> list = service.findByOrderMasterId(Convert.toInt(orderMasterId));
		System.out.println("List<OrderDetailBean> list = " + list);
		
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(list);
		info.setMsg("orderMasterId = " + orderMasterId + "    的資料");
		writeValueByStream(res, info); //把info這個物件, 轉成JSON之後寫回給前端

		
	}
}
