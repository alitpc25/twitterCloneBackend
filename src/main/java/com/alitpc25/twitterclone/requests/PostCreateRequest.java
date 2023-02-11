package com.alitpc25.twitterclone.requests;

public class PostCreateRequest {
	
	private String text;
	private String imageId;
	
	public PostCreateRequest(String text, String imageId) {
		this.text = text;
		this.setImageId(imageId);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
