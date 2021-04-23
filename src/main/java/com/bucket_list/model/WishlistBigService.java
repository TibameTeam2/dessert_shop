package com.bucket_list.model;

public class WishlistBigService {
	
	private WishlistBigDao dao;
	
	public WishlistBigService() {
		dao = new WishlistBigDaoImpl();
	}
	
	public WishlistBigBean getImage(Integer product_id) {
		return dao.getImage(product_id);
	}

}
