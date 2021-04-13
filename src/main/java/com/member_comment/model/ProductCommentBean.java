package com.member_comment.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class ProductCommentBean implements Serializable {
	private String member_account; //PK
	private String member_name;
	private String member_photo;
	private Integer product_id;
	private Integer review_id;
	private Integer rating;
	private String comment_content;
	private Timestamp comment_time;
	private Integer review_image_id;
	private List<String> review_image; 
	private Integer reply_id;
	private String reply_content;
	private Timestamp reply_time;
	@Override
	public String toString() {
		return "ProductCommentBean [member_account=" + member_account + ", member_name=" + member_name
				+ ", member_photo=" + member_photo + ", product_id=" + product_id + ", review_id=" + review_id
				+ ", rating=" + rating + ", comment_content=" + comment_content + ", comment_time=" + comment_time
				+ ", review_image_id=" + review_image_id + ", review_image=" + review_image + ", reply_id=" + reply_id
				+ ", reply_content=" + reply_content + ", reply_time=" + reply_time + "]";
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
	public Timestamp getComment_time() {
		return comment_time;
	}
	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
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
	
	
	
}
