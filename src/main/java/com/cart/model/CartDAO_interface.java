package com.cart.model;

import java.util.List;

public interface CartDAO_interface {
	
	public void insert(CartBean cartBean);
	public void update(CartBean cartBean);
	public void delete(Integer cart_id);
	public CartBean findByPrimaryKey(Integer cart_id);
	public List<CartBean> getAll();

}
