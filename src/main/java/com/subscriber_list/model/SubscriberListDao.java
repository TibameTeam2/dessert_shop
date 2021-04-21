package com.subscriber_list.model;

import java.util.List;

public interface SubscriberListDao {

	/*************************** 新增 **************************/
	public void insert(SubscriberListBean subscriberListBean);

	/*************************** 更新 **************************/
	public void update(SubscriberListBean subscriberListBean);

	/*************************** 刪除 **************************/
	public void delete(Integer subscriber_id);

	/*************************** 查PK **************************/
	public SubscriberListBean findByPrimaryKey(Integer subscriber_id);

	/*************************** 查全部 **************************/
	public List<SubscriberListBean> getAll();

	/*************************** 取得Email **************************/
	public SubscriberListBean findByPrimaryEmail(String subscriber_email);
}
