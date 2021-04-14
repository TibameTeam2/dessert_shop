package com.member_comment.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.review_image_upload.model.ReviewImageUploadBean;

public class MemberCommentService {

	private MemberCommentDao dao;

	public MemberCommentService() {
		dao = new MemberCommentDaoImpl();
	}

	// 用在使用者填完尚未評價後、評價資料進資料庫
	public int addMemberComment(Integer order_detail_id, Integer product_id, Integer rating, String comment_content,
			Integer comment_status) {
		int key = 0;
		MemberCommentBean memberCommentBean = new MemberCommentBean();

		memberCommentBean.setOrder_detail_id(order_detail_id);
		memberCommentBean.setProduct_id(product_id);
		memberCommentBean.setRating(rating);
		memberCommentBean.setComment_content(comment_content);
//		memberCommentBean.setComment_time(comment_time);	//由資料庫產生
		memberCommentBean.setComment_status(1);

		try {
			//insert()用在新增評論並查詢資料庫內的自增主鍵(剛新增的自增主鍵幾號)
			key = dao.insert(memberCommentBean); 
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return key; // 顧客剛寫完送出的評論的review_id(自增主鍵)的編號

	}

	public MemberCommentBean updateMemberComment(Integer review_id, Integer order_detail_id, Integer product_id,
			Integer rating, String comment_content, Integer comment_status) {

		MemberCommentBean memberCommentBean = new MemberCommentBean();

		memberCommentBean.setReview_id(review_id);
		memberCommentBean.setOrder_detail_id(order_detail_id);
		memberCommentBean.setProduct_id(product_id);
		memberCommentBean.setRating(rating);
		memberCommentBean.setComment_content(comment_content);
//		memberCommentBean.setComment_time(comment_time);	//由資料庫產生
		memberCommentBean.setComment_status(comment_status);
		dao.update(memberCommentBean);

		return memberCommentBean;
	}

	public void deleteMemberComment(Integer review_id) {
		dao.delete(review_id);
	}

	public MemberCommentBean getOneMemberComment(Integer review_id) {
		return dao.findByPrimaryKey(review_id);
	}

	public List<MemberCommentBean> getAll() {
		return dao.getAll();
	}

	// 用在0201, memberComment_jsp/select_page.jsp 的 「選擇訂單明細編號」
	public Set<ReviewImageUploadBean> getReviewImageUploadsByReview_id(Integer review_id) {
		return dao.getReviewImageUploadsByReviewId(review_id);
	}

	// 用在使用者送出已填好的尚未評價資料, 查找資料庫自增的review_id
	public MemberCommentBean findReviewIdByOrderDetailId(Integer order_detail_id) {
		return dao.findReviewIdByOrderDetailId(order_detail_id);
	}
}
