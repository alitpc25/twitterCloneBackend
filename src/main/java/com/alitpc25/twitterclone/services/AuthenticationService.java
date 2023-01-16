package com.alitpc25.twitterclone.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.configs.JwtService;
import com.alitpc25.twitterclone.models.Role;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.UserRepository;
import com.alitpc25.twitterclone.requests.AuthenticateRequest;
import com.alitpc25.twitterclone.requests.UserRegisterRequest;
import com.alitpc25.twitterclone.responses.AuthenticationResponse;

@Service
public class AuthenticationService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	public AuthenticationResponse registerUser(UserRegisterRequest userRegisterRequest) {
		// if user email, username exists vs.
		User userToSave = new User(userRegisterRequest.getUsername(), userRegisterRequest.getEmail(), passwordEncoder.encode(userRegisterRequest.getPassword()), Role.USER);
		userRepository.save(userToSave);
		String jwtToken = jwtService.generateToken(userToSave);
		return new AuthenticationResponse(jwtToken, userRegisterRequest.getUsername());
	}
	
	public AuthenticationResponse authenticateUser(AuthenticateRequest authenticateRequest) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUsername(), authenticateRequest.getPassword()));
		User user = userRepository.findByUsername(authenticateRequest.getUsername());
		String jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken, authenticateRequest.getUsername());
	}

}
