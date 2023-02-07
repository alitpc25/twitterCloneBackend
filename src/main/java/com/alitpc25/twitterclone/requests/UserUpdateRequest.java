package com.alitpc25.twitterclone.requests;

import org.springframework.web.multipart.MultipartFile;

public class UserUpdateRequest {

	private String newUsername;
	private MultipartFile image;
	private String username;
	
	public UserUpdateRequest(String newUsername, MultipartFile image, String username) {
		this.setNewUsername(newUsername);
		this.image = image;
		this.username = username;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
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
	
}
