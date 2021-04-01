package com.order_master.model;

import java.util.List;

public interface OrderMasterDao {
	void insert(OrderMasterBean orderMasterBean);
	void update(OrderMasterBean orderMasterBean);
	void delete(Integer order_master_id);
	OrderMasterBean findByPrimaryKey(Integer order_master_id);
	List<OrderMasterBean> getAll();
	List<OrderMasterBean> findByMemberAccount(String member_account);
}
