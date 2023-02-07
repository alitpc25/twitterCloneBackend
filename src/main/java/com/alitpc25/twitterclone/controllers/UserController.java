package com.alitpc25.twitterclone.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.dtos.UserDto;
import com.alitpc25.twitterclone.requests.UserUpdateRequest;
import com.alitpc25.twitterclone.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id) {
    	return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserDto> updateUser(@ModelAttribute UserUpdateRequest request, @PathVariable String id) throws IOException {
		return new ResponseEntity<>(userService.updateUser(request, id) ,HttpStatus.OK);
	}

}
