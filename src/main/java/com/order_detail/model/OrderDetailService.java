package com.order_detail.model;

import java.util.List;

public class OrderDetailService {
	
	private OrderDetailDao dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDaoImpl();
	}

	public List<OrderDetailBean> findByOrderMasterId(Integer order_master_id) {
		return dao.findByOrderMasterId(order_master_id);
	}
	
}
