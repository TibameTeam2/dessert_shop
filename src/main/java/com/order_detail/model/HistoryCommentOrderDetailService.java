package com.order_detail.model;

import java.util.List;

public class HistoryCommentOrderDetailService {
	
	private HistoryCommentOrderDetailDao dao;
	
	public HistoryCommentOrderDetailService() {
		dao = new HistoryCommentOrderDetailDaoImpl();
	}
	
	public List<HistoryCommentOrderDetailBean> findByOrderMasterId(Integer order_master_id) {
		return dao.findByOrderMasterId(order_master_id);
	}

}
