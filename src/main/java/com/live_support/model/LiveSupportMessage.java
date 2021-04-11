package com.live_support.model;

import java.util.Date;

public class LiveSupportMessage {

	private String type; //類似action
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
	
	

	@Override
	public String toString() {
		return "LiveSupportMessage ["
				+ "type=" + type 
				+ ", sender=" + sender 
				+ ", receiver=" + receiver 
				+ ", message="	+ message 
				+ ", chatTime=" + chatTime + "]";
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
