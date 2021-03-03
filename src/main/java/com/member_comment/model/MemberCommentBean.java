package com.member_comment.model;

import java.sql.Timestamp;

public class MemberCommentBean implements java.io.Serializable{

	private Integer review_id;
	private Integer order_detail_id;
	private String comment_content;
	private Integer rating;
	private Timestamp comment_time;
	private Integer product_id;
	private Integer comment_status;
	
	@Override
	public String toString() {
		return "member_commentBean [review_id=" + review_id + ", order_detail_id=" + order_detail_id
				+ ", comment_content=" + comment_content + ", rating=" + rating + ", comment_time=" + comment_time
				+ ", product_id=" + product_id + ", comment_status=" + comment_status + "]";
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Timestamp getComment_time() {
		return comment_time;
	}

	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getComment_status() {
		return comment_status;
	}

	public void setComment_status(Integer comment_status) {
		this.comment_status = comment_status;
	}
	

	
		
}
