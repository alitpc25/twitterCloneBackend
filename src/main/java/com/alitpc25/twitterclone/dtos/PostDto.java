package com.alitpc25.twitterclone.dtos;

public class PostDto {
	private String text;
	private String imageId;
	private String createdDate;
	private String username;
	private String userImage;
	
	public PostDto(String text, String createdDate, String username) {
		this.text = text;
		this.createdDate = createdDate;
		this.username = username;
	}
	public PostDto(String text, String imageId, String createdDate, String username, String userImage) {
		this.text = text;
		this.imageId = imageId;
		this.createdDate = createdDate;
		this.userImage = userImage;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
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
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
