package com.coupon_code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.coupon_code.model.CouponCodeBean;
import com.coupon_code.model.CouponCodeService;
import com.util.BaseServlet;
import com.util.ResultInfo;


public class CouponCodeServlet extends BaseServlet{
	
	public void getCouponCodeData(HttpServletRequest rep , HttpServletResponse res) {
		
		CouponCodeService ccSvc = new CouponCodeService();
		
		List<CouponCodeBean> ccl = ccSvc.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setMsg("資料取得成功");
		info.setData(ccl);
		writeValueByWriter(res, info);
		
	}
	
	
	
}