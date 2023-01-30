package com.alitpc25.twitterclone.utils;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.models.Post;

@Service
public class PostDtoConverter {
	public PostDto convertToDto(Post post) {
		PostDto postDto = new PostDto(post.getText(), Base64.getEncoder().encodeToString(post.getImage().getData()));
	    return postDto;
	}
	
	public List<PostDto> convertToDtoList(List<Post> posts) {
		return posts.stream().map(this::convertToDto).collect(Collectors.toList());
	}
}
