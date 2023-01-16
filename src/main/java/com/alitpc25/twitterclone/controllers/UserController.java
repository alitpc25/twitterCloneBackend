package com.alitpc25.twitterclone.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.services.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
    	return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }

}
