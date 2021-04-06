package com.order_master.model;

import java.util.List;

public interface HistoryCommentProductDao {
	
//	public void insert(HistoryCommentProductBean historyCommentProductBean);
//	public void update(HistoryCommentProductBean historyCommentProductBean);
//	public void delete(Integer order_detail_id);
	public List<HistoryCommentProductBean> findByOrderDetailId(Integer order_detail_id);
//	public List<HistoryCommentProductBean> getAll();

}
