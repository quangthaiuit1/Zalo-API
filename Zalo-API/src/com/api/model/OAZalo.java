package com.api.model;

public class OAZalo {
	private int error;
	private String message;
	private String messageId;
	
	public OAZalo() {

	}
	public OAZalo(int err, String message, String messageId) {
		this.error = err;
		this.message = message;
		this.messageId	= messageId;
	}
	
	public int getError() {
		return error;
	}
	public void setError(int err) {
		this.error = err;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
}
