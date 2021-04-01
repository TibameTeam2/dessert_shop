package com.order_master.model;

import java.io.Serializable;
import java.util.Arrays;

public class HistoryCommentProductBean implements Serializable{
	private Integer order_detail_id;
	private Integer product_id;
	private Integer review_id;
	private Integer rating;
	private String comment_content;
	private Integer review_image_id;
	private byte[] review_image;
	private Integer reply_id;
	private String reply_content;
	
	@Override
	public String toString() {
		return "HistoryCommentProductBean [order_detail_id=" + order_detail_id + ", product_id=" + product_id
				+ ", review_id=" + review_id + ", rating=" + rating + ", comment_content=" + comment_content
				+ ", review_image_id=" + review_image_id + ", review_image=" + Arrays.toString(review_image)
				+ ", reply_id=" + reply_id + ", reply_content=" + reply_content + "]";
	}

	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
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

	public Integer getReply_id() {
		return reply_id;
	}

	public void setReply_id(Integer reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	
}
