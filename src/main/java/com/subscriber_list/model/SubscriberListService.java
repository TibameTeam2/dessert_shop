package com.subscriber_list.model;

import java.util.List;

public class SubscriberListService {

	private SubscriberListDaoImpl dao;

	public SubscriberListService() {

		dao = new SubscriberListDaoImpl();
	}

	/********************** 新增 **********************/
	public Boolean addSSubscriberList(SubscriberListBean subscriberListBean) {

		try {
			
			subscriberListBean.setSubscriber_status(1);
			dao.insert(subscriberListBean);
			return true;
			
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	/********************** 修改 **********************/
	public SubscriberListBean updateSubscriberList(SubscriberListBean subscriberListBean) {

		dao.update(subscriberListBean);
		return subscriberListBean;
	}

	/********************** 刪除 **********************/
	public void deleteSubscriberList(Integer subscriber_id) {
		dao.delete(subscriber_id);
	}

	/********************** 查詢(PK) **********************/
	public SubscriberListBean getOneSubscriberList(Integer subscriber_id) {
		return dao.findByPrimaryKey(subscriber_id);
	}

	/*********************** 查詢(全部) ***********************/
	public List<SubscriberListBean> getAll() {
		return dao.getAll();
	}
	
	/*********************** 後臺更新 ***********************/
	public Boolean backend_updateSubscriber(SubscriberListBean subscriberListBean) {		
		
		SubscriberListBean subscriber =dao.findByPrimaryKey(subscriberListBean.getSubscriber_id());
		
		subscriber.setMember_account(subscriberListBean.getMember_account());
		subscriber.setSubscriber_email(subscriberListBean.getSubscriber_email());
		subscriber.setSubscriber_status(subscriberListBean.getSubscriber_status());
		
		dao.update(subscriber);
		
		System.out.println("已更新" + subscriber + "狀態");
		return true;		
	}
	
	
	
	/*********************** 檢查Email ***********************/
	public SubscriberListBean getSubscriberEmail(String subscriber_email) {		
		return dao.findByPrimaryEmail(subscriber_email);		
	}
	
}
