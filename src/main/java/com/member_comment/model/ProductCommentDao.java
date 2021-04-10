package com.member_comment.model;

import java.util.List;

public interface ProductCommentDao {
	public List<ProductCommentBean> findByProductId(Integer product_id);	

}
