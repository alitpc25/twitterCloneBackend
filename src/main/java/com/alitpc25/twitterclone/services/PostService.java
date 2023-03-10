package com.alitpc25.twitterclone.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.models.Post;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.PostRepository;
import com.alitpc25.twitterclone.requests.PostCreateRequest;
import com.alitpc25.twitterclone.utils.PostDtoConverter;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final PostDtoConverter postDtoConverter;
	private final UserService userService;
	
	public PostService(PostRepository postRepository, PostDtoConverter postDtoConverter, UserService userService) {
		this.postRepository = postRepository;
		this.postDtoConverter = postDtoConverter;
		this.userService = userService;
	}

	public List<PostDto> getAllByUserId(String userId) {
		List<Post> posts = postRepository.findByUserId(userId);
		return posts.stream().map(post -> postDtoConverter.convertToDto(post)).collect(Collectors.toList());
	}
	
	public List<PostDto> getAllByUsername(String username) {
		User user = userService.getByUsernamePriv(username);
		return getAllByUserId(user.getId());
	}
	
	public PostDto getById(String id) {
		Post post = postRepository.findById(id).get();
		return postDtoConverter.convertToDto(post);
	}

	public PostDto createPost(PostCreateRequest request, String userId) throws IOException {
		Post post = new Post(request.getText());
		if(request.getImageId() != null) {
			post.setImageId(request.getImageId());
		}
		User user = userService.getByIdPriv(userId);
		post.setUser(user);
		postRepository.save(post);
		return postDtoConverter.convertToDto(post);
	}

	public PostDto deletePost(String id) {
		Post post = postRepository.findById(id).get();
		postRepository.deleteById(id);
		return postDtoConverter.convertToDto(post);
	}

	public List<PostDto> getAllPostsOfFollowingsByUsername(String username) {
		List<User> users = userService.getFollowedUsersPriv(username);
		List<ObjectId> userIds = users.stream().map(user -> new ObjectId(user.getId())).collect(Collectors.toList());
		List<Post> postsOfFollowings = new ArrayList<Post>(postRepository.findByUserIdOrderByCreatedDateAsc(userIds, Sort.by(Sort.Direction.DESC,"createdDate"))); 
		return postsOfFollowings.stream().map(post -> postDtoConverter.convertToDto(post)).collect(Collectors.toList());
	}
}
