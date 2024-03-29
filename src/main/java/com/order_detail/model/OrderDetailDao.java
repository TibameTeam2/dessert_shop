package com.order_detail.model;

import java.util.List;


public interface OrderDetailDao {

	public void insert(OrderDetailBean orderDetailBean);
	public void update(OrderDetailBean orderDetailBean);
	public void delete(Integer order_detail_id);
	public OrderDetailBean findByPrimaryKey(Integer order_detail_id);
	public List<OrderDetailBean> getAll();
	public List<OrderDetailBean> findByOrderMasterId(Integer order_master_id);
	public List<OrderDetailBean> getAllByOrderMasterId(Integer order_master_id);
	
}
