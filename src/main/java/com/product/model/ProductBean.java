package com.product.model;

import java.io.Serializable;

public class ProductBean implements Serializable {
	private Integer product_id;
	private String product_name;
	private String product_type;
	private String product_subtype;
	private String product_intro;
	private String product_ingredient;
	private Integer product_price;
	private Integer product_available_qty;
	private Integer product_status;
	private Integer expiry_after_buying;
	private Integer product_calorie;
	private Integer degree_of_sweetness;
	private Integer total_star;
	private Integer total_review;
	private Integer total_purchase;
	
	@Override
	public String toString() {
		return "ProductBean [product_id=" + product_id + ", product_name=" + product_name + ", product_type="
				+ product_type + ", product_subtype=" + product_subtype + ", product_intro=" + product_intro
				+ ", product_ingredient=" + product_ingredient + ", product_price=" + product_price
				+ ", product_available_qty=" + product_available_qty + ", product_status=" + product_status
				+ ", expiry_after_buying=" + expiry_after_buying + ", product_calorie=" + product_calorie
				+ ", degree_of_sweetness=" + degree_of_sweetness + ", total_star=" + total_star + ", total_review="
				+ total_review + ", total_purchase=" + total_purchase + "]";
	}
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
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
	public String getProduct_subtype() {
		return product_subtype;
	}
	public void setProduct_subtype(String product_subtype) {
		this.product_subtype = product_subtype;
	}
	public String getProduct_intro() {
		return product_intro;
	}
	public void setProduct_intro(String product_intro) {
		this.product_intro = product_intro;
	}
	public String getProduct_ingredient() {
		return product_ingredient;
	}
	public void setProduct_ingredient(String product_ingredient) {
		this.product_ingredient = product_ingredient;
	}
	public Integer getProduct_price() {
		return product_price;
	}
	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	public Integer getProduct_available_qty() {
		return product_available_qty;
	}
	public void setProduct_available_qty(Integer product_available_qty) {
		this.product_available_qty = product_available_qty;
	}
	public Integer getProduct_status() {
		return product_status;
	}
	public void setProduct_status(Integer product_status) {
		this.product_status = product_status;
	}
	public Integer getExpiry_after_buying() {
		return expiry_after_buying;
	}
	public void setExpiry_after_buying(Integer expiry_after_buying) {
		this.expiry_after_buying = expiry_after_buying;
	}
	public Integer getProduct_calorie() {
		return product_calorie;
	}
	public void setProduct_calorie(Integer product_calorie) {
		this.product_calorie = product_calorie;
	}
	public Integer getDegree_of_sweetness() {
		return degree_of_sweetness;
	}
	public void setDegree_of_sweetness(Integer degree_of_sweetness) {
		this.degree_of_sweetness = degree_of_sweetness;
	}
	public Integer getTotal_star() {
		return total_star;
	}
	public void setTotal_star(Integer total_star) {
		this.total_star = total_star;
	}
	public Integer getTotal_review() {
		return total_review;
	}
	public void setTotal_review(Integer total_review) {
		this.total_review = total_review;
	}
	public Integer getTotal_purchase() {
		return total_purchase;
	}
	public void setTotal_purchase(Integer total_purchase) {
		this.total_purchase = total_purchase;
	}
	
	
	
	

}
