package com.alitpc25.twitterclone.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.mongodb.lang.Nullable;

@Document
public class Post implements Comparable<Post> {
	
	@Id
	@GeneratedValue(generatorClass = UUIDStringGenerator.class)
	private String id;
	
	@Nullable
	private String text;
	
	@Nullable
	private String imageId;
	
	@DocumentReference(lazy=true)
	@Indexed
	private User user;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Post() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Post(String text) {
		this.text = text;
	}
	public Post(String text, String imageId) {
		this.text = text;
		this.setImageId(imageId);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	@Override
	public int compareTo(Post o) {
		if(createdDate==o.createdDate)  
				return 0;  
		else if(createdDate.isAfter(o.createdDate))  
			return 1;  
		else  
			return -1;  
	}
	
}
