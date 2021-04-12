package com.review_image_upload.model;

public class ReviewImageUploadBean implements java.io.Serializable {

	private Integer review_image_id;
	private byte[] review_image; 
	private Integer review_id;
	
	
	@Override
	public String toString() {
		return "review_image_uploadBean [review_image_id=" + review_image_id + ", review_image=" + review_image
				+ ", review_id=" + review_id + "]";
	}

	public Integer getReview_image_id() {
		return review_image_id;
	}

	public void setReview_image_id(Integer review_image_id) {
		this.review_image_id = review_image_id;
	}

	public byte[] getReview_image() {
		return review_image;
	}

	public void setReview_image(byte[] review_image) {
		this.review_image = review_image;
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	
		
	
}
