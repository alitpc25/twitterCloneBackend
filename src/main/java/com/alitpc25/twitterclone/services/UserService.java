package com.alitpc25.twitterclone.services;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.UserRepository;

import dtos.UserDto;
import requests.UserRegisterRequest;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public Optional<UserDto> getByUsername(String username) {
		return null;
	}
	
	private UserDto convertToDto(User user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
	    return userDto;
	}
	
	private User convertToEntity(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
	    return user;
	}

	public UserDto registerUser(UserRegisterRequest userRegisterRequest) {
		// if user email, username exists vs.
		User userToSave = new User(userRegisterRequest.getUsername(), userRegisterRequest.getEmail(), passwordEncoder.encode(userRegisterRequest.getPassword()));
		return convertToDto(userRepository.save(userToSave));
	}

}
