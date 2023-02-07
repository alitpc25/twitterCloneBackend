package com.alitpc25.twitterclone.responses;

public class AuthenticationResponse {
	
	private String token;
	private String username;
	private String userId;
	private String image;

	public AuthenticationResponse(String token, String username, String userId, String image) {
		this.token = token;
		this.userId = userId;
		this.username = username;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
