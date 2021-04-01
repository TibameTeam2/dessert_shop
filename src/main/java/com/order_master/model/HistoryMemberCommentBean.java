package com.order_master.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class HistoryMemberCommentBean {

	String member_account;
	Integer order_master_id;
	Timestamp order_time;
	Timestamp payment_time;
	String invoice_number;
	String coupon_id;
	Integer payment_method;
	String order_remarks;
	Timestamp comment_time;
	byte[] product_image;
	String product_name;
	Integer rating;
	String comment_content;
	byte[] review_image;
	String reply_content;
	
	@Override
	public String toString() {
		return "HistoryMemberCommentBean [member_account=" + member_account + ", order_master_id=" + order_master_id
				+ ", order_time=" + order_time + ", payment_time=" + payment_time + ", invoice_number=" + invoice_number
				+ ", coupon_id=" + coupon_id + ", payment_method=" + payment_method + ", order_remarks=" + order_remarks
				+ ", comment_time=" + comment_time + ", product_image=" + Arrays.toString(product_image)
				+ ", product_name=" + product_name + ", rating=" + rating + ", comment_content=" + comment_content
				+ ", review_image=" + Arrays.toString(review_image) + ", reply_content=" + reply_content + "]";
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public Integer getOrder_master_id() {
		return order_master_id;
	}

	public void setOrder_master_id(Integer order_master_id) {
		this.order_master_id = order_master_id;
	}

	public Timestamp getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public Timestamp getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(Timestamp payment_time) {
		this.payment_time = payment_time;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Integer getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(Integer payment_method) {
		this.payment_method = payment_method;
	}

	public String getOrder_remarks() {
		return order_remarks;
	}

	public void setOrder_remarks(String order_remarks) {
		this.order_remarks = order_remarks;
	}

	public Timestamp getComment_time() {
		return comment_time;
	}

	public void setComment_time(Timestamp comment_time) {
		this.comment_time = comment_time;
	}

	public byte[] getProduct_image() {
		return product_image;
	}

	public void setProduct_image(byte[] product_image) {
		this.product_image = product_image;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public byte[] getReview_image() {
		return review_image;
	}

	public void setReview_image(byte[] review_image) {
		this.review_image = review_image;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	
	

}
