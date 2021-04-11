package com.live_support.model;

import java.util.Set;

public class LiveSupportState {
	
	
	private String type;
	// 使用者改變狀態
	private String user;
	// 全部使用者
	private Set<String> users;

	public LiveSupportState(String type, String user, Set<String> users) {
		super();
		this.type = type;
		this.user = user;
		this.users = users;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

}
