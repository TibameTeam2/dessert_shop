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
	private Integer comment_status;
	private Integer reply_id;
	private String reply_content;
	private Timestamp reply_time;
	private String employee_account;
	private String product_name;
	private String product_type;
	private Integer image_id;
	private String product_image;
	private String member_account;
	private String member_name;
	private String member_photo;
	private Integer review_image_id;
	private List<String> review_image;
	@Override
	public String toString() {
		return "ListMemberCommentBean [review_id=" + review_id + ", order_detail_id=" + order_detail_id
				+ ", product_id=" + product_id + ", comment_content=" + comment_content + ", rating=" + rating
				+ ", comment_time=" + comment_time + ", comment_status=" + comment_status + ", reply_id=" + reply_id
				+ ", reply_content=" + reply_content + ", reply_time=" + reply_time + ", employee_account="
				+ employee_account + ", product_name=" + product_name + ", product_type=" + product_type + ", image_id="
				+ image_id + ", product_image=" + product_image + ", member_account=" + member_account
				+ ", member_name=" + member_name + ", member_photo=" + member_photo + ", review_image_id="
				+ review_image_id + ", review_image=" + review_image + "]";
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
	public Integer getComment_status() {
		return comment_status;
	}
	public void setComment_status(Integer comment_status) {
		this.comment_status = comment_status;
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
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
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
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_photo() {
		return member_photo;
	}
	public void setMember_photo(String member_photo) {
		this.member_photo = member_photo;
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
