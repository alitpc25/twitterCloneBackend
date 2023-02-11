package com.alitpc25.twitterclone.requests;

public class UserUpdateRequest {

	private String newUsername;
	private String username;
	private String imageId;
	
	public UserUpdateRequest(String newUsername, String username, String imageId) {
		this.newUsername = newUsername;
		this.username = username;
		this.setImageId(imageId);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewUsername() {
		return newUsername;
	}
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	
}
