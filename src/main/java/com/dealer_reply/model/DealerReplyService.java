package com.dealer_reply.model;

import java.util.List;

import com.member_comment.model.MemberCommentBean;

public class DealerReplyService {

	private DealerReplyDao dao;

	public DealerReplyService() {
		dao = new DealerReplyDaoImpl();
	}

	// addDealerReply
	public DealerReplyBean addDealerReply(Integer review_id, String reply_content, String employee_account) {

		DealerReplyBean dealerReplyBean = new DealerReplyBean();

		dealerReplyBean.setReview_id(review_id);
		dealerReplyBean.setReply_content(reply_content);
		dealerReplyBean.setEmployee_account(employee_account);
		dao.insert(dealerReplyBean);

		return dealerReplyBean;

	}

	// updateDealerReply
	public DealerReplyBean updateDealerReply(Integer review_id, String reply_content, String employee_account) {

		DealerReplyBean dealerReplyBean = new DealerReplyBean();

		dealerReplyBean.setReview_id(review_id);
		dealerReplyBean.setReply_content(reply_content);
		dealerReplyBean.setEmployee_account(employee_account);
		dao.update(dealerReplyBean);

		return dealerReplyBean;

	}

	// deleteDealerReply
	public void deleteDealerReply(Integer reply_id) {
		dao.delete(reply_id);
	}

	// getOneDealerReply
	public DealerReplyBean getOneDealerReply(Integer reply_id) {
		return dao.findByPrimaryKey(reply_id);
	}

	// getAll
	public List<DealerReplyBean> getAll() {
		return dao.getAll();
	}

}
