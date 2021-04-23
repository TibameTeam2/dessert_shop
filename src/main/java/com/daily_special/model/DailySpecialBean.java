package com.daily_special.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DailySpecialBean implements Serializable{
	private Integer discount_product_id;
	private Integer product_id;
	private Integer discount_price;
	private Timestamp discount_start_time;
	private Timestamp discount_deadline;
	private Integer discount_status;
	
	
	@Override
	public String toString() {
		return "DailySpecialBean [discount_product_id=" + discount_product_id + ", product_id=" + product_id
				+ ", discount_price=" + discount_price + ", discount_start_time=" + discount_start_time
				+ ", discount_deadline=" + discount_deadline + ", discount_status=" + discount_status + "]";
	}
	
	
	
	public Integer getDiscount_product_id() {
		return discount_product_id;
	}
	
	public void setDiscount_product_id(Integer discount_product_id) {
		this.discount_product_id = discount_product_id;
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	
	public Integer getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(Integer discount_price) {
		this.discount_price = discount_price;
	}
	
	public Timestamp getDiscount_start_time() {
		return discount_start_time;
	}
	
	public void setDiscount_start_time(Timestamp discount_start_time) {
		this.discount_start_time = discount_start_time;
	}
	
	public Timestamp getDiscount_deadline() {
		return discount_deadline;
	}
	
	public void setDiscount_deadline(Timestamp discount_deadline) {
		this.discount_deadline = discount_deadline;
	}

	public Integer getDiscount_status() {
		return discount_status;
	}

	public void setDiscount_status(Integer discount_status) {
		this.discount_status = discount_status;
	}

	
	
}
