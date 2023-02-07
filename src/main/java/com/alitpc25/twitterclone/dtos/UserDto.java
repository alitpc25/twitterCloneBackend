package com.alitpc25.twitterclone.dtos;

public class UserDto {
    private String username;
    private String image;
    
    public UserDto() {}
    
	public UserDto(String username, String image) {
		this.username = username;
		this.image = image;
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
}
