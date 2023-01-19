package com.alitpc25.twitterclone.services;

import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.repositories.PostRepository;

@Service
public class PostService {
	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
}
