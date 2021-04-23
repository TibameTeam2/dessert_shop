package com.bucket_list.model;

import java.io.Serializable;
import java.util.List;

public class WishlistBigBean implements Serializable{
	private Integer bucket_list_id;
	private String member_account;
	private Integer product_id;
	private Integer bucket_list_status;
	private String product_name;
	private String product_type;
	private String product_subtype;
	private Integer product_price;
	private Integer product_available_qty;
	private Integer discount_price;
	private Integer image_id;
	private String product_image;
	@Override
	public String toString() {
		return "WishlistBigBean [bucket_list_id=" + bucket_list_id + ", member_account=" + member_account
				+ ", product_id=" + product_id + ", bucket_list_status=" + bucket_list_status + ", product_name="
				+ product_name + ", product_type=" + product_type + ", product_subtype=" + product_subtype
				+ ", product_price=" + product_price + ", product_available_qty=" + product_available_qty
				+ ", discount_price=" + discount_price + ", image_id=" + image_id + ", product_image=" + product_image
				+ "]";
	}
	public Integer getBucket_list_id() {
		return bucket_list_id;
	}
	public void setBucket_list_id(Integer bucket_list_id) {
		this.bucket_list_id = bucket_list_id;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getBucket_list_status() {
		return bucket_list_status;
	}
	public void setBucket_list_status(Integer bucket_list_status) {
		this.bucket_list_status = bucket_list_status;
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
	public Integer getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(Integer discount_price) {
		this.discount_price = discount_price;
	}
	public Integer getImage_id() {
		return image_id;
	}
	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	

	
}
