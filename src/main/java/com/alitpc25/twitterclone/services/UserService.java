package com.alitpc25.twitterclone.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.exceptions.UserNotFoundException;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.UserRepository;
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
		return users.stream().map(userDtoConverter::convertToDto).collect(Collectors.toList());
	}

	public UserDto getByUsername(String username) {
		return userDtoConverter.convertToDto(getByUsernamePriv(username));
	}
	
	public User getByUsernamePriv(String username) {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException();
		}
		return user;
	}

}
