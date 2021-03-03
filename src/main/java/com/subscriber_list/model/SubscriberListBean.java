package com.subscriber_list.model;

import java.sql.Timestamp;

public class SubscriberListBean {
	
	private Integer subscriber_id ; //流水號
	private String 	subscriber_email;
	private Integer	subscriber_status;
	private Timestamp    subscriber_date; //自動新增
	private String  member_account;
	
	
	@Override
	public String toString() {
		return "subscriber_listBean ["  
				+ "subscriber_id=" + subscriber_id 
				+ ", subscriber_email=" + subscriber_email
				+ ", subscriber_status=" + subscriber_status 
				+ ", subscriber_date=" + subscriber_date
				+ ", member_account=" + member_account + "]";
	}


	public Integer getSubscriber_id() {
		return subscriber_id;
	}


	public void setSubscriber_id(Integer subscriber_id) {
		this.subscriber_id = subscriber_id;
	}


	public String getSubscriber_email() {
		return subscriber_email;
	}


	public void setSubscriber_email(String subscriber_email) {
		this.subscriber_email = subscriber_email;
	}


	public Integer getSubscriber_status() {
		return subscriber_status;
	}


	public void setSubscriber_status(Integer subscriber_status) {
		this.subscriber_status = subscriber_status;
	}


	public Timestamp getSubscriber_date() {
		return subscriber_date;
	}


	public void setSubscriber_date(Timestamp subscriber_date) {
		this.subscriber_date = subscriber_date;
	}


	public String getMember_account() {
		return member_account;
	}


	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	

	
	
}
