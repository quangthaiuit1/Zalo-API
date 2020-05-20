package com.api.model;

public class User {
	private int error;
	private String userId;
	private String displayName;
	
	public User() {
		
	}
	public User(int error, String userId, String displayName) {
		this.userId = userId;
		this.displayName = displayName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
}
