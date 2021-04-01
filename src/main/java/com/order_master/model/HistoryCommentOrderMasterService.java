package com.order_master.model;

import java.util.List;

public class HistoryCommentOrderMasterService {

	private HistoryCommentOrderMasterDao dao;
	
	public HistoryCommentOrderMasterService() {
		dao = new HistoryCommentOrderMasterDaoImpl();
	}
	
	public List<HistoryCommentOrderMasterBean> getOrderMasterByMemberAccount(String member_account) {
		return dao.findByMemberAccount(member_account);
	}
	
	
}
