package com.member_comment.model;

import java.util.List;

public interface YetToLeaveCommentDao {

	public List<YetToLeaveCommentBean> findByMemberAccount(String member_account);
	public List<YetToLeaveCommentBean> findByOrderMasterId(Integer order_master_id);

}
