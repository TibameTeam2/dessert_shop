package com.order_master.model;

import java.io.Serializable;
import java.util.Arrays;

public class HistoryCommentProductBean implements Serializable{
	
	private Integer order_master_id;
	private Integer order_detail_id;
	private Integer product_id;
	private Integer review_id;
	private Integer review_image_id;
	private String review_image;
	@Override
	public String toString() {
		return "HistoryCommentProductBean [order_master_id=" + order_master_id + ", order_detail_id=" + order_detail_id
				+ ", product_id=" + product_id + ", review_id=" + review_id + ", review_image_id=" + review_image_id
				+ ", review_image=" + review_image + "]";
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
	public Integer getReview_image_id() {
		return review_image_id;
	}
	public void setReview_image_id(Integer review_image_id) {
		this.review_image_id = review_image_id;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	
}
