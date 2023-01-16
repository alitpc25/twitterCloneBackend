package com.alitpc25.twitterclone.requests;

public class UserRegisterRequest {
    private final String username;
    private final String email;
    private final String password;
    
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public UserRegisterRequest(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
    
}
