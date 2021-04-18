package com.order_master.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberBean;
import com.order_detail.model.OrderDetailBean;
import com.order_detail.model.OrderDetailService;
import com.order_master.model.OrderMasterBean;
import com.order_master.model.OrderMasterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class OrderMasterServlet extends BaseServlet {
	
	OrderMasterService OrderMasterSvc = new OrderMasterService();
	OrderDetailService OrderDetailSvc = new OrderDetailService();
	
	public void getOrderMaster(HttpServletRequest req, HttpServletResponse res) {
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            req.getSession().setAttribute("location", req.getContextPath() + "/TEA103G2/front-end/my-account..html");
			info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
        	
        	List<OrderMasterBean> list_OM = OrderMasterSvc.getOrderMaster(member.getMember_account());
        	List<List> list_OD_All = new ArrayList<List>();
        	for (OrderMasterBean OMBean : list_OM) {
        		List<OrderDetailBean> list_OD = OrderDetailSvc.getAllByOrderMasterId(OMBean.getOrder_master_id());
        		list_OD_All.add(list_OD);
        	}
        	
        	List<List> list = new ArrayList<List>();
        	list.add(list_OM);
        	list.add(list_OD_All);
        	
        	info.setFlag(true);
        	info.setData(list);
        	info.setMsg(member.getMember_account()+"的訂單資料和訂單明細");
        }
        writeValueByWriter(res, info);
	}

	
	
	public void backend_getAll(HttpServletRequest req, HttpServletResponse res) {
		
		List<OrderMasterBean> list = OrderMasterSvc.getAll();
        ResultInfo info = new ResultInfo();
        info.setFlag(true);
        info.setMsg("取得所有訂單資料!");
        info.setData(list);
        writeValueByWriter(res, info);
		
	}
	
	
	public void backend_delete(HttpServletRequest req, HttpServletResponse res) {
		
		Integer order_master_id = new Integer(req.getParameter("order_master_id"));
        ResultInfo info = new ResultInfo();
        try {
        	OrderMasterSvc.deleteOrderMaster(order_master_id);
            info.setFlag(true);
            info.setMsg("已刪除訂單資料!");
        } catch(Exception e) {
            info.setFlag(false);
            info.setMsg("刪除訂單資料失敗，請注意外鍵!");
        }
        writeValueByWriter(res, info);
	
	}

	
}
