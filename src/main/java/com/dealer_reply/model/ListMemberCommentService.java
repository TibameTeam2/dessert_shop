package com.dealer_reply.model;

import java.util.List;

public class ListMemberCommentService {

	private ListMemberCommentDao dao;
	
	public ListMemberCommentService() {
		dao = new ListMemberCommentDaoImpl();
	}
	
	public List<ListMemberCommentBean> findAllCommentContent(){
		return dao.findAllCommentContent();
		
	}
}
