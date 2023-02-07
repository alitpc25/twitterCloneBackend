package com.alitpc25.twitterclone.utils;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.models.User;

@Service
public class UserDtoConverter {

	public UserDto convertToDto(User user) {
		UserDto userDto = new UserDto(user.getUsername(), Base64.getEncoder().encodeToString(user.getImage().getData()));
	    return userDto;
	}
	
	public List<UserDto> convertToDtoList(List<User> users) {
		return users.stream().map(this::convertToDto).collect(Collectors.toList());
	}
}
