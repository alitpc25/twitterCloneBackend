package com.alitpc25.twitterclone.dtos;

public class PostDto {
	private String text;
	private String image;
	
	public PostDto(String text) {
		this.text = text;
	}
	public PostDto(String text, String image) {
		this.text = text;
		this.setImage(image);
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
