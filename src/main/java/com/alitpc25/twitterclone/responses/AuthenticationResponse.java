package com.alitpc25.twitterclone.responses;

public class AuthenticationResponse {
	
	private String token;
	private String username;
	private String userId;
	private String imageId;

	public AuthenticationResponse(String token, String username, String userId, String imageId) {
		this.token = token;
		this.userId = userId;
		this.username = username;
		this.imageId = imageId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
}
