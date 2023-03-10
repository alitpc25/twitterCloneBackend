package com.alitpc25.twitterclone.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.requests.UserChangeCredentialsRequest;
import com.alitpc25.twitterclone.requests.UserFollowRequest;
import com.alitpc25.twitterclone.requests.UserUpdateRequest;
import com.alitpc25.twitterclone.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/*
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    */
    
	@RequestMapping(value = "", method = RequestMethod.GET ,params = "id")
    public ResponseEntity<UserDto> getById(@RequestParam("id") String id) {
    	return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET ,params = "username")
    public ResponseEntity<UserDto> getByUsername(@RequestParam("username") String username) {
    	return new ResponseEntity<>(userService.getByUsername(username), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateRequest request, @PathVariable String id) throws IOException {
		return new ResponseEntity<>(userService.updateUser(request, id) ,HttpStatus.OK);
	}
    
    @PutMapping("/changeCredentials/{id}")
	public ResponseEntity<UserDto> changeCredentials(@RequestBody UserChangeCredentialsRequest request, @PathVariable String id) throws IOException {
		return new ResponseEntity<>(userService.changeCredentials(request, id) ,HttpStatus.OK);
	}
    
    @GetMapping(value="", params={"page", "size", "username"})
	public ResponseEntity<Page<UserDto>> getAllSearchByUsername(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "username", defaultValue = "") String username) {
		return ResponseEntity.ok(userService.getAllSearchByUsername(page, size, username));
	}
    
    @RequestMapping(value = "/follow", method = RequestMethod.PUT)
    public ResponseEntity<UserDto> follow(@RequestBody UserFollowRequest request) {
    	return ResponseEntity.ok(userService.follow(request));
    }
    
    @GetMapping("/getFollowedUsers")
    public ResponseEntity<List<UserDto>> getFollowedUsers(@RequestParam("from") String from) {
    	return ResponseEntity.ok(userService.getFollowedUsers(from));
    }

}
