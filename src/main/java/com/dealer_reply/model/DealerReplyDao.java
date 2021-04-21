package com.dealer_reply.model;

import java.util.List;

import com.member_comment.model.MemberCommentBean;

public interface DealerReplyDao {

	public int insert(DealerReplyBean DealerReplyBean, MemberCommentBean memberCommentBean);

	public void update(DealerReplyBean DealerReplyBean);

	public void delete(Integer reply_id);

	public DealerReplyBean findByPrimaryKey(Integer reply_id);

	public List<DealerReplyBean> getAll();
}
