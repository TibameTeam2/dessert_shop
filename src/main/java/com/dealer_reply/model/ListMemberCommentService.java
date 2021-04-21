package com.dealer_reply.model;

import java.util.List;

public class ListMemberCommentService {

	private ListMemberCommentDao dao;
	
	public ListMemberCommentService() {
		dao = new ListMemberCommentDaoImpl();
	}
	
	// 用在後台顯示所有評論資料
	public List<ListMemberCommentBean> findAllCommentContent(){
		return dao.findAllCommentContent();
		
	}
}
