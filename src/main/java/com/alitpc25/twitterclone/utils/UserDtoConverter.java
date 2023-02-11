package com.alitpc25.twitterclone.utils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.models.User;

@Service
public class UserDtoConverter {

	public UserDto convertToDto(User user) {
		UserDto userDto = new UserDto(user.getUsername(), user.getImageId(), user.getCreatedDate().toString());
	    return userDto;
	}
	
	public Page<UserDto> convertToDtoList(Page<User> users) {
		return users.map(this::convertToDto);
	}
}
