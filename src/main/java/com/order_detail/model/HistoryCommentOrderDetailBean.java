package com.order_detail.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class HistoryCommentOrderDetailBean implements Serializable{

	private Integer order_master_id;
	private Integer order_detail_id;
	private Integer product_id;
	private String product_name;
	private Integer image_id;
	private String product_image;
	private Integer rating;
	private String comment_content;
	private Integer reply_id;
	private String reply_content;
	private List<String> review_image;
	@Override
	public String toString() {
		return "HistoryCommentOrderDetailBean [order_master_id=" + order_master_id + ", order_detail_id="
				+ order_detail_id + ", product_id=" + product_id + ", product_name=" + product_name + ", image_id="
				+ image_id + ", product_image=" + product_image + ", rating=" + rating + ", comment_content="
				+ comment_content + ", reply_id=" + reply_id + ", reply_content=" + reply_content + ", review_image="
				+ review_image + "]";
	}
	public Integer getOrder_master_id() {
		return order_master_id;
	}
	public void setOrder_master_id(Integer order_master_id) {
		this.order_master_id = order_master_id;
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getImage_id() {
		return image_id;
	}
	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
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
	public List<String> getReview_image() {
		return review_image;
	}
	public void setReview_image(List<String> review_image) {
		this.review_image = review_image;
	}
	
	
	
}
