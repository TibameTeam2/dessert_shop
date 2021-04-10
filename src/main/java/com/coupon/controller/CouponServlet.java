package com.coupon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponBean;
import com.coupon.model.CouponService;
import com.util.BaseServlet;
import com.util.ResultInfo;

public class CouponServlet extends BaseServlet {
	
	public void getCouponData(HttpServletRequest req , HttpServletResponse res) {
		
		CouponService cS = new CouponService();
		
		List<CouponBean> cl = cS.getAll();
		
		ResultInfo info = new ResultInfo();
		
		info.setFlag(true);
		info.setData(cl);
		info.setMsg("撈到資料囉!");
		writeValueByWriter(res, info);
	}

}
