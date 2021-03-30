package com.order_master.model;

import java.util.List;

public interface HistoryCommentOrderMasterDao {
	
//	public void insert(HistoryCommentOrderMasterBean historyCommentOrderMasterBean);
//	public void update(HistoryCommentOrderMasterBean historyCommentOrderMasterBean);
//	public void delete(Integer order_master_id);
	public HistoryCommentOrderMasterBean findByPrimaryKey(String member_account);
//	public List<HistoryCommentOrderMasterBean> getAll();

}
