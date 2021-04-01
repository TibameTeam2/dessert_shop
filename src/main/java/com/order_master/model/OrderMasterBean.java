package com.order_master.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderMasterBean implements Serializable {
	
	private Integer order_master_id;
	private String member_account;
	private Timestamp order_time;
	private Timestamp payment_time;
	private	Integer payment_method;
	private Integer coupon_id;
	private Integer order_status;
	private String invoice_number;
	private Integer order_total;
	private String order_remarks;
	
	@Override
	public String toString() {
		return "OrderMasterBean [order_master_id=" + order_master_id + ", member_account=" + member_account
				+ ", order_time=" + order_time + ", payment_time=" + payment_time + ", payment_method=" + payment_method
				+ ", coupon_id=" + coupon_id + ", order_status=" + order_status + ", invoice_number=" + invoice_number
				+ ", order_total=" + order_total + ", order_remarks=" + order_remarks + "]";
	}

	public Integer getOrder_master_id() {
		return order_master_id;
	}

	public void setOrder_master_id(Integer order_master_id) {
		this.order_master_id = order_master_id;
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
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

	public Integer getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(Integer payment_method) {
		this.payment_method = payment_method;
	}

	public Integer getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public String getInvoice_number() {
		return invoice_number;
	}

	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}

	public Integer getOrder_total() {
		return order_total;
	}

	public void setOrder_total(Integer order_total) {
		this.order_total = order_total;
	}

	public String getOrder_remarks() {
		return order_remarks;
	}

	public void setOrder_remarks(String order_remarks) {
		this.order_remarks = order_remarks;
	}
	
	

}
