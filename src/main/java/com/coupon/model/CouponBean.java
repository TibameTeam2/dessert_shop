package com.coupon.model;

import java.sql.Timestamp;

public class CouponBean {

	private Integer coupon_id;
	private String member_account;
	private Timestamp coupon_sending_time;
	private Timestamp coupon_effective_date;
	private Timestamp coupon_expire_date;
	private String coupon_text_content;
	private Float coupon_content;
	private Integer discount_type;
	private Integer coupon_status;
	private String employee_account;
	private Integer coupon_code_id;

	@Override
	public String toString() {
		return "couponBean [coupon_id=" + coupon_id + ", member_account=" + member_account + ", coupon_sending_time="
				+ coupon_sending_time + ", coupon_effective_date=" + coupon_effective_date + ", coupon_expire_date="
				+ coupon_expire_date + ", coupon_text_content=" + coupon_text_content + ", coupon_content="
				+ coupon_content + ", discount_type=" + discount_type + ", coupon_status=" + coupon_status
				+ ", employee_account=" + employee_account + ", coupon_code_id=" + coupon_code_id + "]";
	}

	public Integer getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public Timestamp getCoupon_sending_time() {
		return coupon_sending_time;
	}

	public void setCoupon_sending_time(Timestamp coupon_sending_time) {
		this.coupon_sending_time = coupon_sending_time;
	}

	public Timestamp getCoupon_effective_date() {
		return coupon_effective_date;
	}

	public void setCoupon_effective_date(Timestamp coupon_effective_date) {
		this.coupon_effective_date = coupon_effective_date;
	}

	public Timestamp getCoupon_expire_date() {
		return coupon_expire_date;
	}

	public void setCoupon_expire_date(Timestamp coupon_expire_date) {
		this.coupon_expire_date = coupon_expire_date;
	}

	public String getCoupon_text_content() {
		return coupon_text_content;
	}

	public void setCoupon_text_content(String coupon_text_content) {
		this.coupon_text_content = coupon_text_content;
	}

	public Float getCoupon_content() {
		return coupon_content;
	}

	public void setCoupon_content(Float coupon_content) {
		this.coupon_content = coupon_content;
	}

	public Integer getDiscount_type() {
		return discount_type;
	}

	public void setDiscount_type(Integer discount_type) {
		this.discount_type = discount_type;
	}

	public Integer getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(Integer coupon_status) {
		this.coupon_status = coupon_status;
	}

	public String getEmployee_account() {
		return employee_account;
	}

	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}

	public Integer getCoupon_code_id() {
		return coupon_code_id;
	}

	public void setCoupon_code_id(Integer coupon_code_id) {
		this.coupon_code_id = coupon_code_id;
	}

}
