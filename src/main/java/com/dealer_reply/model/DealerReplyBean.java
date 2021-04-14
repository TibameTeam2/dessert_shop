package com.dealer_reply.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class DealerReplyBean implements Serializable{
	
	private Integer reply_id;
	private Integer review_id;
	private String reply_content;
	private Timestamp reply_time;
	private String employee_account;
	
	@Override
	public String toString() {
		return "dealer_replyBean [reply_id=" + reply_id + ", review_id=" + review_id + ", reply_content="
				+ reply_content + ", reply_time=" + reply_time + ", employee_account=" + employee_account + "]";
	}

	public Integer getReply_id() {
		return reply_id;
	}

	public void setReply_id(Integer reply_id) {
		this.reply_id = reply_id;
	}

	public Integer getReview_id() {
		return review_id;
	}

	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Timestamp getReply_time() {
		return reply_time;
	}

	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}

	public String getEmployee_account() {
		return employee_account;
	}

	public void setEmployee_account(String employee_account) {
		this.employee_account = employee_account;
	}
	
	
}
