package com.review_image_upload.model;

import java.util.List;

public class ReviewImageUploadService {

	private ReviewImageUploadDao dao;
	
	public ReviewImageUploadService() {
		dao = new ReviewImageUploadDaoImpl();
	}
	
	public ReviewImageUploadBean addReviewImageUpload(byte[] review_image, Integer review_id) {
		
		ReviewImageUploadBean reviewImageUploadBean = new ReviewImageUploadBean();
		
		reviewImageUploadBean.setReview_image(review_image);
		reviewImageUploadBean.setReview_id(review_id);
		dao.insert(reviewImageUploadBean);
		
		return reviewImageUploadBean;
	}
	
	public ReviewImageUploadBean updateReviewImageUpload(Integer review_image_id, byte[] review_image, Integer review_id) {
		
		ReviewImageUploadBean reviewImageUploadBean = new ReviewImageUploadBean();
		
		reviewImageUploadBean.setReview_image_id(review_image_id);
		reviewImageUploadBean.setReview_image(review_image);
		reviewImageUploadBean.setReview_id(review_id);
		dao.update(reviewImageUploadBean);
		
		return reviewImageUploadBean;
		
	}
	
	public  void deleteReviewImageUpload(Integer review_image_id) {
		dao.delete(review_image_id);
	}
	
	public ReviewImageUploadBean getOneReviewImageUpload(Integer review_image_id) {
		return dao.findByPrimaryKey(review_image_id);
	}
	
	public List<ReviewImageUploadBean> getAll(){
		return dao.getAll();
	}
}
