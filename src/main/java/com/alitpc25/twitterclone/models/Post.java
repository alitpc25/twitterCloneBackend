package com.alitpc25.twitterclone.models;

public class Post {
	private String id;
	private String text;
	private String image;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Post(String id, String text, String image) {
		this.id = id;
		this.text = text;
		this.image = image;
	}
	
}
