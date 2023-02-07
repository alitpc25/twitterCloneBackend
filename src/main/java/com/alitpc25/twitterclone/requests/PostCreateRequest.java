package com.alitpc25.twitterclone.requests;

import org.springframework.web.multipart.MultipartFile;

public class PostCreateRequest {
	
	private String text;
	private MultipartFile image;
	
	public PostCreateRequest(String text, MultipartFile image) {
		this.text = text;
		this.setImage(image);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
