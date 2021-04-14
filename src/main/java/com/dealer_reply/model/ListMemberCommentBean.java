package com.dealer_reply.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ListMemberCommentBean implements Serializable {
	private Integer review_id;
	private Integer order_detail_id;
	private Integer product_id;
	private String comment_content;
	private Integer rating;
	private Timestamp comment_time;
	private Integer reply_id;
	private String reply_content;
	private Timestamp reply_time;
	private String employee_account;
	private String product_name;
	private Integer review_image_id;
	private List<String> review_image;
	@Override
	public String toString() {
		return "ListMemberCommentBean [review_id=" + review_id + ", order_detail_id=" + order_detail_id
				+ ", product_id=" + product_id + ", comment_content=" + comment_content + ", rating=" + rating
				+ ", comment_time=" + comment_time + ", reply_id=" + reply_id + ", reply_content=" + reply_content
				+ ", reply_time=" + reply_time + ", employee_account=" + employee_account + ", product_name="
				+ product_name + ", review_image_id=" + review_image_id + ", review_image=" + review_image + "]";
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
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	public Timestamp getReply_time() {
		return reply_time;
	}
	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}
	public String getEmployee_account() {
		return employee_account;
	}
	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getReview_image_id() {
		return review_image_id;
	}
	public void setReview_image_id(Integer review_image_id) {
		this.review_image_id = review_image_id;
	}
	public List<String> getReview_image() {
		return review_image;
	}
	public void setReview_image(List<String> review_image) {
		this.review_image = review_image;
	}

	
}
