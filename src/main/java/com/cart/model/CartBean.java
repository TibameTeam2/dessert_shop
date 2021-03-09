package com.cart.model;

public class CartBean {
	
	private Integer cart_id;
	private String member_account;
	private Integer product_id;
	private Integer product_quantity;
	
	@Override
	public String toString() {
		return "cartBean [cart_id=" + cart_id + ", member_account=" + member_account + ", product_id=" + product_id
				+ ", product_quantity=" + product_quantity + "]";
	}

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
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

	public Integer getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	
	
}
