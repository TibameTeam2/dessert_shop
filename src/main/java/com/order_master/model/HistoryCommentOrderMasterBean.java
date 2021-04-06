package com.order_master.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class HistoryCommentOrderMasterBean implements Serializable{
	String member_account;
	Integer order_master_id;
	Timestamp order_time;
	Timestamp payment_time; 
	String invoice_number;
	Integer coupon_id;
	Integer payment_method;
	String order_remarks;
	Timestamp comment_time;
	
	@Override
	public String toString() {
		return "HistoryCommentOrderMasterBean [member_account=" + member_account + ", order_master_id="
				+ order_master_id + ", order_time=" + order_time + ", payment_time=" + payment_time
				+ ", invoice_number=" + invoice_number + ", coupon_id=" + coupon_id + ", payment_method="
				+ payment_method + ", order_remarks=" + order_remarks + ", comment_time=" + comment_time + "]";
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
	
	
	
}
