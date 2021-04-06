package com.order_master.model;

import java.util.List;

public class HistoryCommentProductService {

	private HistoryCommentProductDao dao;
	
	public HistoryCommentProductService() {
		dao = new HistoryCommentProductDaoImpl();
	}
	
	public List<HistoryCommentProductBean> findByOrderDetailId(Integer order_detail_id){
		return dao.findByOrderDetailId(order_detail_id);
	}
}
