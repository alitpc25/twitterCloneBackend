package com.alitpc25.twitterclone.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.repositories.PostRepository;
import com.alitpc25.twitterclone.services.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
}
