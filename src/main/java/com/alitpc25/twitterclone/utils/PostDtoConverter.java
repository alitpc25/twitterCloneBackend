package com.alitpc25.twitterclone.utils;

import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.models.Post;

@Service
public class PostDtoConverter {
	public PostDto convertToDto(Post post) {
		PostDto postDto = new PostDto(post.getText(), post.getCreatedDate().toString(), post.getUser().getUsername());
		if(post.getImageId() != null) {
			postDto.setImageId(post.getImageId());
		}
		if(post.getUser().getImageId() != null) {
			postDto.setUserImage(post.getUser().getImageId());
		}
	    return postDto;
	}
}
