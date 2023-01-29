package com.alitpc25.twitterclone.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

public class Post {
	
	@Id
	@GeneratedValue(generatorClass = UUIDStringGenerator.class)
	private String id;
	
	private String text;
	private String image;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Post(String text, String image) {
		this.text = text;
		this.image = image;
	}
	
}
