package com.dealer_reply.model;

import java.util.List;

public interface DealerReplyDao {

	public void insert(DealerReplyBean DealerReplyBean);

	public void update(DealerReplyBean DealerReplyBean);

	public void delete(Integer reply_id);

	public DealerReplyBean findByPrimaryKey(Integer reply_id);

	public List<DealerReplyBean> getAll();
}
