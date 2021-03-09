package com.order_detail.model;

public class OrderDetailBean {
	private Integer order_detail_id;
	private Integer order_master_id;
	private Integer product_id;
	private Integer product_qty;
	private Integer product_price;
	
	@Override
	public String toString() {
		return "OrderDetailBean [order_detail_id=" + order_detail_id + ", order_master_id=" + order_master_id
				+ ", product_id=" + product_id + ", product_qty=" + product_qty + ", product_price=" + product_price
				+ "]";
	}

	public Integer getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public Integer getOrder_master_id() {
		return order_master_id;
	}

	public void setOrder_master_id(Integer order_master_id) {
		this.order_master_id = order_master_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getProduct_qty() {
		return product_qty;
	}

	public void setProduct_qty(Integer product_qty) {
		this.product_qty = product_qty;
	}

	public Integer getProduct_price() {
		return product_price;
	}

	public void setProduct_price(Integer product_price) {
		this.product_price = product_price;
	}
	

}
