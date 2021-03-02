package com.notice.model;

import java.sql.Timestamp;

public class NoticeBean {
	
	private Integer 	notice_id;
	private Integer 	notice_type;
	private String  	notice_content;
	private Timestamp   notice_time;
	private Integer		read_status;
	private String  	member_account;
	
	
	@Override
	public String toString() {
		return "noticeBean ["
				+ "notice_id=" + notice_id 
				+ ", notice_type=" + notice_type 
				+ ", notice_content=" + notice_content 
				+ ", notice_time=" + notice_time 
				+ ", read_status=" + read_status 
				+ ", member_account=" + member_account + "]";
	}
	
	
	public Integer getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}
	public Integer getNotice_type() {
		return notice_type;
	}
	public void setNotice_type(Integer notice_type) {
		this.notice_type = notice_type;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Timestamp getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(Timestamp notice_time) {
		this.notice_time = notice_time;
	}
	public Integer getRead_status() {
		return read_status;
	}
	public void setRead_status(Integer read_status) {
		this.read_status = read_status;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	
	
	
}
