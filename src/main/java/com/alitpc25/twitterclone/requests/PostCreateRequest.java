package com.alitpc25.twitterclone.requests;

public class PostCreateRequest {
	
	private String text;
	private String image;
	
	public PostCreateRequest(String text, String image) {
		this.text = text;
		this.image = image;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
