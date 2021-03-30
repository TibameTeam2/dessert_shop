package com.order_master.model;

public class HistoryCommentOrderMasterService {

	private HistoryCommentOrderMasterDao dao;
	
	private HistoryCommentOrderMasterService() {
		dao = new HistoryCommentOrderMasterDaoImpl();
	}
	
	public HistoryCommentOrderMasterBean getOneHistoryCommentOrderMaster(Integer order_master_id) {
		return dao.findByPrimaryKey(member_account);
	}
	
	
}
