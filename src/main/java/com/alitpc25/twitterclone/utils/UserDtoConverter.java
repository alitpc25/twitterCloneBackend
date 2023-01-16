package com.alitpc25.twitterclone.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.models.User;

@Service
public class UserDtoConverter {
	
	private final ModelMapper modelMapper;
	
	public UserDtoConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	
	public User convertToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
	    return user;
	}
}
