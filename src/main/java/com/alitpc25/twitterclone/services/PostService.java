package com.alitpc25.twitterclone.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.PostDto;
import com.alitpc25.twitterclone.models.Post;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.PostRepository;
import com.alitpc25.twitterclone.requests.PostCreateRequest;
import com.alitpc25.twitterclone.utils.PostDtoConverter;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final PostDtoConverter postDtoConverter;
	private final UserService userService;
	private final Storage firebaseStorage;
	
	public PostService(PostRepository postRepository, PostDtoConverter postDtoConverter, UserService userService, Storage firebaseStorage) {
		this.postRepository = postRepository;
		this.postDtoConverter = postDtoConverter;
		this.userService = userService;
		this.firebaseStorage = firebaseStorage;
	}

	public List<PostDto> getAllByUserId(String userId) {
		List<Post> posts = postRepository.findAllByUserId(userId);
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
		BlobId blobId = BlobId.of("twitter-clone-app-d3449.appspot.com", post.getImageId());
		postRepository.deleteById(id);
		firebaseStorage.delete(blobId);
		return postDtoConverter.convertToDto(post);
	}

	public List<PostDto> getAllPostsOfFollowingsByUsername(String username) {
		List<User> users = userService.getFollowedUsersPriv(username);
		List<ObjectId> userIds = users.stream().map(user -> new ObjectId(user.getId())).collect(Collectors.toList());
		List<Post> postsOfFollowings = new ArrayList<Post>(postRepository.findAllByUserIdOrderByCreatedDate(userIds)); 
		return postsOfFollowings.stream().map(post -> postDtoConverter.convertToDto(post)).collect(Collectors.toList());
	}
}
