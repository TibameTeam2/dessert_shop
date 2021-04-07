package com.order_detail.model;

import java.util.List;

public interface HistoryCommentOrderDetailDao {
	
	public List<HistoryCommentOrderDetailBean> findByOrderMasterId(Integer order_master_id);

}
