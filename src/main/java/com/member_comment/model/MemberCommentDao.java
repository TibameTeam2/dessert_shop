package com.member_comment.model;

import java.util.List;
import java.util.Set;

import com.review_image_upload.model.ReviewImageUploadBean;

public interface MemberCommentDao {
	public void insert(MemberCommentBean memberCommentBean);
	public void update(MemberCommentBean memberCommentBean);
    public void delete(Integer review_id);
    public MemberCommentBean findByPrimaryKey(Integer review_id);
    public List<MemberCommentBean> getAll();
//	萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<MemberCommentBean> getAll(Map<String, String[]> map);
    
//  查詢某review_id的review_image_upload們的資料(一個review_id裡有最多三筆review_image_upload資料)
    public Set<ReviewImageUploadBean> getReviewImageUploadsByReview_id(Integer review_id);
}
