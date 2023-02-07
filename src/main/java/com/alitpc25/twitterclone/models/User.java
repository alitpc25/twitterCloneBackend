package com.alitpc25.twitterclone.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class User {
	@Id
    private String id;

    private String username;
    private String email;
    private String password;
    private Role role;
    
    private Binary image;
    
	private Set<User> followings;
	
	@DocumentReference
	private Set<Post> posts;
    
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

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}
	
	public Binary getImage() {
		return image;
	}
	public void setImage(Binary image) {
		this.image = image;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
