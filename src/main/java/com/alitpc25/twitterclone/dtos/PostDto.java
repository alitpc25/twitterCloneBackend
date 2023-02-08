package com.alitpc25.twitterclone.dtos;

public class PostDto {
	private String text;
	private String image;
	private String createdDate;
	
	public PostDto(String text, String createdDate) {
		this.text = text;
		this.createdDate = createdDate;
	}
	public PostDto(String text, String image, String createdDate) {
		this.text = text;
		this.image = image;
		this.createdDate = createdDate;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
