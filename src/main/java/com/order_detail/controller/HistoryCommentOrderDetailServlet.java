package com.order_detail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_detail.model.HistoryCommentOrderDetailBean;
import com.order_detail.model.HistoryCommentOrderDetailService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)

public class HistoryCommentOrderDetailServlet extends BaseServlet {

	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}

	public void getHistoryCommentOrderDetail(HttpServletRequest req, HttpServletResponse res) {

		String orderMasterId = req.getParameter("orderMasterId");
		System.out.println("orderMasterId = " + orderMasterId);

		HistoryCommentOrderDetailService service = new HistoryCommentOrderDetailService();
		List<HistoryCommentOrderDetailBean> list = service.findByOrderMasterId(Convert.toInt(orderMasterId));
		System.out.println("List<HistoryCommentOrderDetailBean> list = " + list);

		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(list);
		info.setMsg("orderMasterId = " + orderMasterId + "    的資料");
		writeValueByStream(res, info); // 把info這個物件, 轉成JSON之後寫回給前端

	}
}
