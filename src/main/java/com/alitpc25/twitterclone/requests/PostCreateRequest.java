package com.alitpc25.twitterclone.requests;

import org.springframework.web.multipart.MultipartFile;

public class PostCreateRequest {
	
	private String text;
	private MultipartFile image;
	private String username;
	
	public PostCreateRequest(String text, MultipartFile image, String username) {
		this.setUsername(username);
		this.text = text;
		this.setImage(image);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
