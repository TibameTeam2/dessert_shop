package com.cart.model;

import java.util.List;

public class CartProductService {	
		
	CartProductDAO dao = new CartProductDAO();
	
	public List<CartProductBean> getCartDataByMemberAccount(String member_account) {
		
		return dao.selectByMemberAccount(member_account);
		
	}
	

}
