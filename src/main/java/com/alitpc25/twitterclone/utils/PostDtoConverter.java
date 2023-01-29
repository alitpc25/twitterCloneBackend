package com.alitpc25.twitterclone.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.models.Post;

public class PostDtoConverter {
	public PostDto convertToDto(Post post) {
		PostDto postDto = new PostDto(post.getText(), post.getImage());
	    return postDto;
	}
	
	public List<PostDto> convertToDtoList(List<Post> posts) {
		return posts.stream().map(this::convertToDto).collect(Collectors.toList());
	}
}
