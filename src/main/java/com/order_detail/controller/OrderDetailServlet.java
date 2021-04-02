package com.order_detail.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order_detail.model.OrderDetailService;
import com.order_master.model.HistoryCommentOrderMasterBean;
import com.order_master.model.OrderMasterBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
public class OrderDetailServlet extends BaseServlet {

	OrderDetailService orderDetailSvc = new OrderDetailService();
	public void test(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("hello");
	}
	
	/**
	 * 在HistoryCommentOrderMasterServlet確認member為已登入且member_account='amy'是有order_master_id
	 * (且是撈得到不只一筆order_master_id)
	 * 
	 * 接著要在這支OrderDetailServlet裡列出每個order_master_id所對應的多個order_detail_id
	 * 
	 */
	
	public void getHistoryCommentOrderDetail(HttpServletRequest req, HttpServletResponse res) {
		
		//omBean得到order_detail_id, order_master_id, product_id, product_qty, product_price
		OrderMasterBean omBean = (OrderMasterBean) req.getSession().getAttribute("order_detail");
//		HistoryCommentOrderMasterBean hcomBean = (HistoryCommentOrderMasterBean) req.getSession().getAttribute("order_master");
		ResultInfo info = new ResultInfo();
		System.out.println(info);
//		if(hcomBean==null) {
//			info.setFlag(false);
//            info.setMsg("無order_master_id(無歷史訂單)!");
//		}else {
//			Integer order_master_id = hcomBean.getOrder_master_id();
//			
//		}
		
	}
}
