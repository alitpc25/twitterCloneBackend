package com.alitpc25.twitterclone.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitpc25.twitterclone.requests.AuthenticateRequest;
import com.alitpc25.twitterclone.requests.UserLoginRequest;
import com.alitpc25.twitterclone.requests.UserRegisterRequest;
import com.alitpc25.twitterclone.responses.AuthenticationResponse;
import com.alitpc25.twitterclone.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest) {
		AuthenticationResponse authenticationResponse = authenticationService.registerUser(userRegisterRequest);
    	return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }
    
    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserLoginRequest userLoginRequest) {
    	AuthenticationResponse authenticationResponse = authenticationService.loginUser(userLoginRequest);
    	return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}
