package com.order_master.controller;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemberBean;
import com.order_detail.model.OrderDetailBean;
import com.order_detail.model.OrderDetailService;
import com.order_master.model.OrderMasterBean;
import com.order_master.model.OrderMasterService;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.digest.DigestUtil;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 50 * 1024 * 1024, maxRequestSize = 50 * 1024 * 1024)
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
	
	
	public void backend_addOrderMaster(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//獲取數據
        Map<String, String[]> map1 = req.getParameterMap();
        Map map = new HashMap(map1);
        System.out.println("map= " + new ObjectMapper().writeValueAsString(map));
        
        String[] str = map1.get("payment_time")[0].split("T");
        String str_ts = str[0]+" "+str[1];
        
        java.sql.Timestamp ts = new java.sql.Timestamp(Convert.toDate(str_ts).getTime());
        map.replace("payment_time", ts);
        
        //封裝物件
        OrderMasterBean orderMasterBean = new OrderMasterBean();
        try {
            BeanUtils.populate(orderMasterBean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
        boolean flag = OrderMasterSvc.addOrderMaster(orderMasterBean);
        
        ResultInfo info = new ResultInfo();
        //創建結果 準備返回前端
        if (flag) {
            //新增成功
            info.setFlag(true);
            info.setMsg("新增成功!");
        } else {
            //新增失敗
            info.setFlag(false);
            info.setMsg("新增失敗 請注意外鍵!");
        }
        
        writeValueByWriter(res, info);
	
	}
	
	
	

	
}
