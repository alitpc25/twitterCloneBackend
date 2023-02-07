package com.alitpc25.twitterclone.services;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.exceptions.UserNotFoundException;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.models.UserDetail;
import com.alitpc25.twitterclone.repositories.UserRepository;
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

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return userDtoConverter.convertToDtoList(users);
	}

	public UserDto getById(String id) {
		return userDtoConverter.convertToDto(getByIdPriv(id));
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
		Binary newImageBinary;
		try {
			newImageBinary = new Binary(BsonBinarySubType.BINARY, request.getImage().getBytes());
			if(newImageBinary != user.getImage()) {
				user.setImage(newImageBinary);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
