package com.live_support.model;

import java.sql.Timestamp;

public class LiveSupportMessage {

	private String type;
	private String sender;
	private String receiver;
	private String chat_history;

	public LiveSupportMessage(String type, String sender, String receiver, String chat_history) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.chat_history = chat_history;
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

	public String getChat_history() {
		return chat_history;
	}

	public void setChat_history(String chat_history) {
		this.chat_history = chat_history;
	}

}
