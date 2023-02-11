package com.alitpc25.twitterclone.models;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class User {
	@Id
    private String id;

	@Indexed
    private String username;
	
    private String email;
    private String password;
    private Role role;
    
    private String imageId;
    
    @DocumentReference
	private Set<User> followings = new HashSet<>();
	
	@CreatedDate
	private LocalDateTime createdDate;

	private User() {}
    
    public User(String username) {this.username = username;}
    
	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.setRole(role);
	}
	
	public void hasFriendshipWith(User person) {
		if (followings == null) {
			followings = new HashSet<>();
	    }
		followings.add(person);
  	}
	
	public String toString() {
		    return this.username + "'s friends => "
		    	+ Optional.ofNullable(this.followings).orElse(
		    	Collections.emptySet()).stream()
		    	.map(User::getUsername)
		    	.collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<User> getFollowings() {
		return followings;
	}
	
    public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
