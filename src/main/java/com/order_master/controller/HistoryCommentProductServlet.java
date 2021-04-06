package com.order_master.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_master.model.HistoryCommentProductBean;
import com.order_master.model.HistoryCommentProductService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class HistoryCommentProductServlet extends BaseServlet {
       
	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}
	
    public void getHistoryCommentProduct(HttpServletRequest req, HttpServletResponse res) {
    	
    	String orderDetailId = req.getParameter("orderDetailId");
    	System.out.println("orderDetailId = " + orderDetailId);
    	
    	HistoryCommentProductService historyCommentProductSvc = new HistoryCommentProductService();
    	List<HistoryCommentProductBean> list = historyCommentProductSvc.findByOrderDetailId(Convert.toInt(orderDetailId));
    	System.out.println("List<HistoryCommentProductBean> list = " + list);
    	
    	ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(list);
		info.setMsg("orderDetailId = " + orderDetailId + "    的資料");
		writeValueByStream(res, info); //把info這個物件, 轉成JSON之後寫回給前端
    	  	
    	
    }

}
