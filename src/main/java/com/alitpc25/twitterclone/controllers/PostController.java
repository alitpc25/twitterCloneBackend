package com.alitpc25.twitterclone.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.requests.PostCreateRequest;
import com.alitpc25.twitterclone.services.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET ,params = "userId")
	public ResponseEntity<List<PostDto>> getAllByUserId(@RequestParam String userId) {
		return new ResponseEntity<>(postService.getAllByUserId(userId) ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET ,params = "username")
	public ResponseEntity<List<PostDto>> getAllByUsername(@RequestParam String username) {
		return new ResponseEntity<>(postService.getAllByUsername(username) ,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getById(@PathVariable String postId) {
		return new ResponseEntity<>(postService.getById(postId) ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostDto> createPost(@ModelAttribute PostCreateRequest request, @RequestParam String userId) throws IOException {
		return new ResponseEntity<>(postService.createPost(request, userId) ,HttpStatus.OK);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<PostDto> deletePost(@PathVariable String id) {
		return new ResponseEntity<>(postService.deletePost(id) ,HttpStatus.OK);
	}
}
