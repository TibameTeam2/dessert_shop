package com.cart.model;

public class CartProductBean implements java.io.Serializable {
	
	private Integer cart_id;
	private Integer product_id;
	private Integer product_quantity;
	private String product_name;
	private Integer product_price;
	
	@Override
	public String toString() {
		return "CartProductBean [cart_id=" + cart_id + ", product_id=" + product_id + ", product_quantity="
				+ product_quantity + ", product_name=" + product_name + ", product_price=" + product_price + "]";
	}

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
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

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	

}
