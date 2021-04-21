package com.dealer_reply.model;

import java.sql.Timestamp;
import java.util.List;

import com.member_comment.model.MemberCommentBean;

public class DealerReplyService {

	private DealerReplyDao dao;

	public DealerReplyService() {
		dao = new DealerReplyDaoImpl();
	}

	// 用在業者新增回覆
	public int addDealerReply(Integer review_id, String reply_content, String employee_account) {

		int key = 0;
		DealerReplyBean dealerReplyBean = new DealerReplyBean();
		MemberCommentBean memberCommentBean = new MemberCommentBean();

		dealerReplyBean.setReview_id(review_id);
		dealerReplyBean.setReply_content(reply_content);
		dealerReplyBean.setEmployee_account(employee_account);
		memberCommentBean.setReview_id(review_id);
		
		try {
			key = dao.insert(dealerReplyBean, memberCommentBean);
			System.out.println("在service的key: "+key);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return key;
		
		
	}

	// updateDealerReply
	public DealerReplyBean updateDealerReply(Integer review_id, String reply_content, Timestamp reply_time, String employee_account) {

		DealerReplyBean dealerReplyBean = new DealerReplyBean();

		dealerReplyBean.setReview_id(review_id);
		dealerReplyBean.setReply_content(reply_content);
		dealerReplyBean.setReply_time(reply_time);
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
