package com.member_comment.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class YetToLeaveCommentBean implements Serializable{
	private String member_account;
	private Integer order_master_id;
	private Integer order_detail_id;
	private Timestamp order_time;
	private Timestamp payment_time; 
	private String invoice_number;
	private Integer coupon_id;
	private Integer payment_method;
	private String order_remarks;
	private Timestamp comment_time;
	private byte[] product_image;
	private String product_name;
	private Integer product_id;
	@Override
	public String toString() {
		return "YetToLeaveCommentBean [member_account=" + member_account + ", order_master_id=" + order_master_id
				+ ", order_detail_id=" + order_detail_id + ", order_time=" + order_time + ", payment_time="
				+ payment_time + ", invoice_number=" + invoice_number + ", coupon_id=" + coupon_id + ", payment_method="
				+ payment_method + ", order_remarks=" + order_remarks + ", comment_time=" + comment_time
				+ ", product_image=" + Arrays.toString(product_image) + ", product_name=" + product_name
				+ ", product_id=" + product_id + "]";
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
	public Integer getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
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
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
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
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	
}
