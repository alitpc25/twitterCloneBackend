package com.alitpc25.twitterclone.dtos;

public class UserDto {
    private String username;
    private String imageId;
    private String createdDate;
    
    public UserDto() {}
    
	public UserDto(String username, String imageId, String createdDate) {
		this.username = username;
		this.imageId = imageId;
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
