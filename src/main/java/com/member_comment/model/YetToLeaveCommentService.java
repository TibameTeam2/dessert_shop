package com.member_comment.model;

import java.util.List;

public class YetToLeaveCommentService {

	private YetToLeaveCommentDao dao;
	
	public YetToLeaveCommentService() {
		dao = new YetToLeaveCommentDaoImpl();
	}
	
	public List<YetToLeaveCommentBean> findByMemberAccount(String member_account){
		return dao.findByMemberAccount(member_account);
	}
	public List<YetToLeaveCommentBean> findByOrderMasterId(Integer order_master_id){
		return dao.findByOrderMasterId(order_master_id);
	}
}
