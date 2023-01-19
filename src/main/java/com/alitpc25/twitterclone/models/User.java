package com.alitpc25.twitterclone.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Node
public class User implements UserDetails {
	@Id
	@GeneratedValue 
    private Long id;

    private String username;
    private String email;
    private String password;
    private Role role;
    
    private User() {}
    
    public User(String username) {this.username = username;}
    
	public User(String username, String email, String password, Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	private Set<User> followers;
	
	@Relationship(type = "FOLLOWING")
	private Set<User> followings;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Set<User> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	public Set<User> getFollowings() {
		return followers;
	}

	public void setFollowings(Set<User> followings) {
		this.followings = followings;
	}
	
}
