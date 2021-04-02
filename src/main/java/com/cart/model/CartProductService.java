package com.cart.model;

import java.util.ArrayList;
import java.util.List;

public class CartProductService {	
		
	CartProductDAO dao = new CartProductDAO();
	
	//拿cart_id, cart.product_id, product_quantity, product_name, product_price,coupon_id, coupon_text_content
	public List getCartDataByMemberAccount(String member_account) {
				
		List list = dao.selectByMemberAccount(member_account);
		
		list.addAll(dao.selectCouponByMemberAccount(member_account));
		
//		list.addALL();
		
		return list;
		
	}
	

}
