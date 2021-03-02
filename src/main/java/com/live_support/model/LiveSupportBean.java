package com.live_support.model;

import java.sql.Timestamp;

public class LiveSupportBean {

	private Integer customer_service_id;
	private String chat_history;
	private Integer sender;
	private Timestamp chat_time;
	private String member_account;
	private String employee_account;

	@Override
	public String toString() {
		return "live_supportBean [" 
				+ "customer_service_id=" + customer_service_id 
				+ ", chat_history=" + chat_history
				+ ", sender=" + sender 
				+ ", chat_time=" + chat_time 
				+ ", member_account=" + member_account
				+ ", employee_account=" + employee_account + "]";
	}

	public Integer getCustomer_service_id() {
		return customer_service_id;
	}

	public void setCustomer_service_id(Integer customer_service_id) {
		this.customer_service_id = customer_service_id;
	}

	public String getChat_history() {
		return chat_history;
	}

	public void setChat_history(String chat_history) {
		this.chat_history = chat_history;
	}

	public Integer getSender() {
		return sender;
	}

	public void setSender(Integer sender) {
		this.sender = sender;
	}

	public Timestamp getChat_time() {
		return chat_time;
	}

	public void setChat_time(Timestamp chat_time) {
		this.chat_time = chat_time;
	}

	public String getMember_account() {
		return member_account;
	}

	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}

	public String getEmployee_account() {
		return employee_account;
	}

	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}

}
