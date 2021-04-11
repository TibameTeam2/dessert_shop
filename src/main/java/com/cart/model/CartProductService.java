package com.cart.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.coupon.model.CouponBean;
import com.coupon_code.model.CouponCodeBean;

public class CartProductService {	
		
	CartProductDAO dao = new CartProductDAO();
	
	//拿cart_id, cart.product_id, product_quantity, product_name, product_price/ product_image的src
	public List<List> getCartDataByMemberAccount(String member_account, String contextPath) {
		
		List<List> list = new ArrayList<List>();
		
		List<CartProductBean> list1 = dao.selectByMemberAccount(member_account);
		list.add(list1);
		
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			String src = contextPath+"/cart/getProductImage?product_id="+list1.get(i).getProduct_id();
			list2.add(src);
		}
		list.add(list2);
		
		return list;
		
	}
	
	//拿coupon資料
	public List<CouponBean> getCouponDataByMemberAccount(String member_account) {
		
		return dao.selectCouponByMemberAccount(member_account);		
		
	}	
	
	//回傳圖片資料流
	public InputStream getProductImageByProductId(Integer product_id) {
		
		return dao.getFirstImageByProductId(product_id);
		
	}	
	
	//update購物車內商品數量
	public void updateQuantityAtCart(Integer cart_id, Integer product_quantity) {
		
		dao.updateProductQuantity(cart_id, product_quantity);
		
	}
	
	//delete購物車內商品
	public void deleteProductAtCart(Integer cart_id) {
		
		dao.delete(cart_id);
		
	}
	
	//查詢優惠碼
	public CouponCodeBean selectCouponCode(String coupon_code) {
			
		return dao.selectCouponCodeData(coupon_code);
		
	}
	//使用會員帳號+優惠碼查詢單筆優惠券對照 以及回傳用
	public CouponBean selectCoupon(String member_account, Integer coupon_code_id) {
		
		return dao.selectCouponData(member_account, coupon_code_id);
		
	} 
	//insert優惠券
	public void insertCoupon(CouponBean CB) {
		
		dao.insertCouponData(CB);
		
	}
	
	
	

}
