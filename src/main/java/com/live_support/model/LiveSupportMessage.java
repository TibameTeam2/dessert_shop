package com.live_support.model;

import java.util.Date;

public class LiveSupportMessage {

	private String type; //分辨為聊天還是歷史訊息
	private String sender;
	private String receiver;
	private String message;
	private Date chatTime;
	
	public LiveSupportMessage(String type, String sender, String receiver, String message, Date chatTime) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.chatTime = chatTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getChatTime() {
		return chatTime;
	}

	public void setChatTime(Date chatTime) {
		this.chatTime = chatTime;
	}
	

	

	
}
