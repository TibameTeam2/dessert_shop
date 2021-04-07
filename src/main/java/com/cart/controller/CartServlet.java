package com.cart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartProductBean;
import com.cart.model.CartProductService;
import com.coupon.model.CouponBean;
import com.coupon_code.model.CouponCodeBean;
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
	
	
	//使用優惠碼新增優惠券
	public void insertCoupon(HttpServletRequest req, HttpServletResponse res) {
		
		MemberBean member = (MemberBean) req.getSession().getAttribute("member");
		ResultInfo info = new ResultInfo();
		if (member == null) {
            info.setFlag(false);
            info.setMsg("尚未登入!");
            info.setRedirect(req.getContextPath() + "/TEA103G2/front-end/login.html");
        } else {
        	//取資料
			String coupon_code = req.getParameter("coupon_code").trim();
			//新增優惠券
			
			//檢查優惠碼是否存在且未過期
			CouponCodeBean CCB = svc.selectCouponCode(coupon_code);
			java.sql.Timestamp ts_now = new java.sql.Timestamp(System.currentTimeMillis());
			if (CCB != null && ts_now.getTime() < CCB.getCoupon_code_expire_date().getTime()) {
				//檢查該會員是否有該優惠碼生成的優惠券
				CouponBean CB_exist = svc.selectCoupon(member.getMember_account(), CCB.getCoupon_code_id());
				if (CB_exist == null) {
					
					CouponBean CB_new = new CouponBean();
					CB_new.setMember_account(member.getMember_account());
					CB_new.setCoupon_sending_time(ts_now);
					CB_new.setCoupon_effective_date(ts_now);
					CB_new.setCoupon_expire_date(CCB.getCoupon_code_expire_date());
					CB_new.setCoupon_text_content(CCB.getcoupon_code_text_content());
					CB_new.setCoupon_content(CCB.getcoupon_code_content());
					CB_new.setDiscount_type(CCB.getDiscount_type());
					CB_new.setCoupon_status(0);
					CB_new.setEmployee_account(CCB.getEmployee_account());
					CB_new.setCoupon_code_id(CCB.getCoupon_code_id());
					svc.insertCoupon(CB_new);
					
					info.setFlag(true);
			        info.setMsg("成功使用優惠碼新增優惠券!");
					
				} else {
					info.setFlag(false);
			        info.setMsg("優惠碼已使用過!");
				}
				
			} else {
				info.setFlag(false);
		        info.setMsg("優惠碼無效!");
			}
				
        }
		
		writeValueByWriter(res, info);
		
	}
	
	
	
	

}
