package com.order_detail.model;

import java.util.List;

public class OrderDetailService {
	
	private OrderDetailDao dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDaoImpl();
	}

	public List<OrderDetailBean> getOneOrderDetail() {
		return dao.getAllProductNameImageIncluded();
	}
	
}
