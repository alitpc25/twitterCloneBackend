package com.alitpc25.twitterclone.requests;

public class UserLoginRequest {
    private final String email;
    private final String password;
    
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public UserLoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
