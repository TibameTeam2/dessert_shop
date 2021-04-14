package com.dealer_reply.model;

import java.util.List;

public interface ListMemberCommentDao {
	
	public void update(ListMemberCommentBean listMemberCommentBean);

	public void delete(Integer reply_id);

	public ListMemberCommentBean findByPrimaryKey(Integer reply_id);

	public List<ListMemberCommentBean> findAllCommentContent();
	
	//是否該來個萬用?

}
