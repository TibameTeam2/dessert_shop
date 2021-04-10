package com.member_comment.model;

import java.util.List;

public class ProductCommentService {
	
	private ProductCommentDao dao;
	
	public ProductCommentService() {
		dao = new ProductCommentDaoImpl();
	}
	
	public List<ProductCommentBean> findByProductId(Integer product_id) {
		return dao.findByProductId(product_id);
	}

}
