package com.order_master.model;

import java.util.List;

public class OrderMasterService {

	private OrderMasterDao dao;

	public OrderMasterService() {
		dao = new OrderMasterDaoImpl();
	}

	public OrderMasterBean addOrderMaster(String member_account, Integer payment_method, Integer coupon_id,
			Integer order_status, String invoice_number, Integer order_total, String order_remarks) {

		OrderMasterBean orderMasterBean = new OrderMasterBean();
		orderMasterBean.setMember_account(member_account);
		orderMasterBean.setPayment_method(payment_method);
		orderMasterBean.setCoupon_id(coupon_id);
		orderMasterBean.setOrder_status(order_status);
		orderMasterBean.setInvoice_number(invoice_number);
		orderMasterBean.setOrder_total(order_total);
		orderMasterBean.setOrder_remarks(order_remarks);
		dao.insert(orderMasterBean);

		return orderMasterBean;

	}
	
	
	public OrderMasterBean updateOrderMaster(Integer payment_method, Integer coupon_id,
			Integer order_status, String invoice_number, Integer order_total, String order_remarks) {
				
		OrderMasterBean orderMasterBean = new OrderMasterBean();
		orderMasterBean.setPayment_method(payment_method);
		orderMasterBean.setCoupon_id(coupon_id);
		orderMasterBean.setOrder_status(order_status);
		orderMasterBean.setInvoice_number(invoice_number);
		orderMasterBean.setOrder_total(order_total);
		orderMasterBean.setOrder_remarks(order_remarks);
		dao.update(orderMasterBean);
		
		return orderMasterBean;
		
	}
	
	
	public void deleteOrderMaster(Integer order_master_id) {
		dao.delete(order_master_id);
	}
	
	
	public OrderMasterBean getOneOrderMaster(Integer order_master_id) {
		return dao.findByPrimaryKey(order_master_id);
	}
	
	public List<OrderMasterBean> getOrderMasterByMemberAccount(String member_account){
		return dao.findByMemberAccount(member_account);
	}
	
	public List<OrderMasterBean> getAll(){
		return dao.getAll();
	}
	
	public List<OrderMasterBean> getOrderMaster(String member_account) {
		return dao.getOrderMaster(member_account);
	}
	
	
	public boolean addOrderMaster(OrderMasterBean orderMasterBean) {
		
		try {
            dao.insert(orderMasterBean);
            return true;
        } catch (Exception e) {
            return false;
        }	
		
	}
	
	
	
	
	
	
}
