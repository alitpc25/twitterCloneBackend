package com.alitpc25.twitterclone.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.exceptions.UserNotFoundException;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.models.UserDetail;
import com.alitpc25.twitterclone.repositories.UserRepository;
import com.alitpc25.twitterclone.requests.UserFollowRequest;
import com.alitpc25.twitterclone.requests.UserUpdateRequest;
import com.alitpc25.twitterclone.utils.UserDtoConverter;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserDtoConverter userDtoConverter;

	public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
		this.userRepository = userRepository;
		this.userDtoConverter = userDtoConverter;
	}
	/*
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return userDtoConverter.convertToDtoList(users);
	}
	*/

	public UserDto getById(String id) {
		return userDtoConverter.convertToDto(getByIdPriv(id));
	}
	
	public UserDto getByUsername(String username) {
		return userDtoConverter.convertToDto(getByUsernamePriv(username));
	}
	
	public User getByIdPriv(String id) {
		User user = userRepository.findById(id).get();
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}
	
	public User getByUsernamePriv(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

	public UserDto updateUser(UserUpdateRequest request, String id) {
		User user = getByIdPriv(id);
		if(user.getUsername() != request.getUsername()) {
			//Throw bad credentials exception.
		}
		if(request.getUsername() != request.getNewUsername()) {
			user.setUsername(request.getNewUsername());
		}
		if(user.getImageId() == null && request.getImageId() != null){
			user.setImageId(request.getImageId());
		}
		return userDtoConverter.convertToDto(userRepository.save(user));
	}

	public UserDetails getByEmailPriv(String email) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return new UserDetail(user.getEmail(), user.getPassword(), user.getRole());
	}

	public Page<UserDto> getAllSearchByUsername(Integer page, Integer size, String username) {
		Page<User> users = userRepository.findByUsernameContainingIgnoreCase(PageRequest.of(page-1, size), username);
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("There exists no candidate called "+ username);
		}
		return userDtoConverter.convertToDtoList(users);
	}

	public UserDto follow(UserFollowRequest request) {
		User senderUser = getByUsernamePriv(request.getSender());
		User receiverUser = getByUsernamePriv(request.getReceiver());
		senderUser.getFollowings().add(receiverUser);
		return userDtoConverter.convertToDto(userRepository.save(senderUser));
	}

	public List<UserDto> getFollowedUsers(String from) {
		User fromUser = getByUsernamePriv(from);
		List<User> followedUsers = new ArrayList<User>(fromUser.getFollowings());
		return followedUsers.stream().map(userDtoConverter::convertToDto).toList();
	}
	
	public List<User> getFollowedUsersPriv(String from) {
		User fromUser = getByUsernamePriv(from);
		List<User> followedUsers = new ArrayList<User>(fromUser.getFollowings());
		return followedUsers;
	}

}
