package com.alitpc25.twitterclone.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.services.UserService;

import dtos.UserDto;
import exceptions.UserNotFoundException;
import requests.UserRegisterRequest;

@CrossOrigin
@RestController
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
    @GetMapping("users")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {

        Optional<UserDto> user = userService.getByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new UserNotFoundException();
        }
    }
    
    @PostMapping(path = "users")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {

    	UserDto userDto = userService.registerUser(userRegisterRequest);
    	return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

}
