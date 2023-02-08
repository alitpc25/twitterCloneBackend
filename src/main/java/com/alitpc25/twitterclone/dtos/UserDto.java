package com.alitpc25.twitterclone.dtos;

public class UserDto {
    private String username;
    private String image;
    private String createdDate;
    
    public UserDto() {}
    
	public UserDto(String username, String image, String createdDate) {
		this.username = username;
		this.image = image;
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
