package com.coupon_code.model;

import java.sql.Timestamp;

public class CouponCodeBean {
	
	private Integer coupon_code_id;
	private String coupon_code;
	private Timestamp coupon_code_effective_date;
	private Timestamp coupon_code_expire_date;
	private String coupon_code_text_content;
	private Float coupon_code_text;
	private Integer discount_type;
	private String employee_account;
	
	@Override
	public String toString() {
		return "coupon_codeBean [coupon_code_id=" + coupon_code_id + ", coupon_code=" + coupon_code
				+ ", coupon_code_effective_date=" + coupon_code_effective_date + ", coupon_code_expire_date="
				+ coupon_code_expire_date + ", coupon_code_text_content=" + coupon_code_text_content
				+ ", coupon_code_text=" + coupon_code_text + ", discount_type=" + discount_type + ", employee_account="
				+ employee_account + "]";
	}
	
	public Integer getCoupon_code_id() {
		return coupon_code_id;
	}
	public void setCoupon_code_id(Integer coupon_code_id) {
		this.coupon_code_id = coupon_code_id;
	}
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}
	public Timestamp getCoupon_code_effective_date() {
		return coupon_code_effective_date;
	}
	public void setCoupon_code_effective_date(Timestamp coupon_code_effective_date) {
		this.coupon_code_effective_date = coupon_code_effective_date;
	}
	public Timestamp getCoupon_code_expire_date() {
		return coupon_code_expire_date;
	}
	public void setCoupon_code_expire_date(Timestamp coupon_code_expire_date) {
		this.coupon_code_expire_date = coupon_code_expire_date;
	}
	public String getCoupon_code_text_content() {
		return coupon_code_text_content;
	}
	public void setCoupon_code_text_content(String coupon_code_text_content) {
		this.coupon_code_text_content = coupon_code_text_content;
	}
	public Float getCoupon_code_text() {
		return coupon_code_text;
	}
	public void setCoupon_code_text(Float coupon_code_text) {
		this.coupon_code_text = coupon_code_text;
	}
	public Integer getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(Integer discount_type) {
		this.discount_type = discount_type;
	}
	public String getEmployee_account() {
		return employee_account;
	}
	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	
	

}
