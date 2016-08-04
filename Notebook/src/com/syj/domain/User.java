package com.syj.domain;

import java.util.Set;

public class User {
	private Integer id;
	private String username;
	private String password;
	private Set<Message> sendMessages;
	private Set<Message> receiveMessages;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Message> getSendMessages() {
		return sendMessages;
	}
	public void setSendMessages(Set<Message> sendMessages) {
		this.sendMessages = sendMessages;
	}
	public Set<Message> getReceiveMessages() {
		return receiveMessages;
	}
	public void setReceiveMessages(Set<Message> receiveMessages) {
		this.receiveMessages = receiveMessages;
	}
	
}
