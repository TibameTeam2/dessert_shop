package com.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartProductBean;
import com.cart.model.CartProductService;
import com.coupon.model.CouponBean;
import com.member.model.MemberBean;
import com.util.BaseServlet;
import com.util.ResultInfo;

import cn.hutool.core.io.IoUtil;

public class CartServlet extends BaseServlet {
	
	CartProductService svc = new CartProductService();
	
	//取得購物車資料
	public void getCartData(HttpServletRequest req, HttpServletResponse res) {
		
		//從session取得member
        MemberBean member = (MemberBean) req.getSession().getAttribute("member");
        ResultInfo info = new ResultInfo();
        if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
            List<List> list = svc.getCartDataByMemberAccount(member.getMember_account(), req.getContextPath());    
            info.setData(list);    
            
            info.setFlag(true);
            info.setMsg("已將購物車和圖片src資料載入!");
        }
        
        writeValueByWriter(res, info);
		
	}
	
	
	//取得優惠券資料
	public void getCouponData(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
	        List<CouponBean> list = svc.getCouponDataByMemberAccount(member.getMember_account());
	        info.setData(list);
	        
	        info.setFlag(true);
	        info.setMsg("已將優惠券資料載入!");
        }
		
		writeValueByWriter(res, info);
		
	}
	
	
	//印出圖片
	public void getProductImage(HttpServletRequest req, HttpServletResponse res) {
		
		//取得product_id
		Integer product_id = new Integer(req.getParameter("product_id"));
		
		res.setContentType("image/png");
		
		try {
			IoUtil.write(res.getOutputStream(), true, IoUtil.readBytes(svc.getProductImageByProductId(product_id), true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	//更新購物車內商品數量
	public void updateQuantity(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {		
			//取資料
			Integer cart_id = new Integer(req.getParameter("cart_id"));
			Integer product_quantity = new Integer(req.getParameter("product_quantity"));
			//更新數量
			svc.updateQuantityAtCart(cart_id, product_quantity);
			
			info.setFlag(true);
	        info.setMsg("已更新數量!");
        }
		
		writeValueByWriter(res, info);
		
	}
	
	
	//刪除購物車內商品
	public void deleteProduct(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
        	//取資料
			Integer cart_id = new Integer(req.getParameter("cart_id"));
			//刪除商品
			svc.deleteProductAtCart(cart_id);
			
			info.setFlag(true);
	        info.setMsg("已刪除商品!");
        }
		
		writeValueByWriter(res, info);
		
	}
	
	

}
