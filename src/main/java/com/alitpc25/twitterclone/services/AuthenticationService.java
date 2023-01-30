package com.alitpc25.twitterclone.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alitpc25.twitterclone.configs.JwtService;
import com.alitpc25.twitterclone.exceptions.BadCredentialsException;
import com.alitpc25.twitterclone.exceptions.EmailAlreadyInUseException;
import com.alitpc25.twitterclone.exceptions.UserNotFoundException;
import com.alitpc25.twitterclone.exceptions.UsernameAlreadyInUseException;
import com.alitpc25.twitterclone.models.Role;
import com.alitpc25.twitterclone.models.User;
import com.alitpc25.twitterclone.repositories.UserRepository;
import com.alitpc25.twitterclone.requests.UserLoginRequest;
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
		if(userRepository.existsByUsername(userRegisterRequest.getUsername())) {
			throw new UsernameAlreadyInUseException("Username is already in use.");
		}
		
		if(userRepository.existsByEmail(userRegisterRequest.getEmail())) {
			throw new EmailAlreadyInUseException("Email is already in use.");
		}
		
		User userToSave = new User(userRegisterRequest.getUsername(), userRegisterRequest.getEmail(), passwordEncoder.encode(userRegisterRequest.getPassword()), Role.USER);
		userRepository.save(userToSave);
		String jwtToken = jwtService.generateToken(userToSave);
		return new AuthenticationResponse(jwtToken, userRegisterRequest.getUsername(), userToSave.getId());
	}
	
	public AuthenticationResponse loginUser(UserLoginRequest userLoginRequest) {
		User user = userRepository.findByEmail(userLoginRequest.getEmail());
		if(user == null) {
			throw new UserNotFoundException("There is no user with this email.");
		}
		if(!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
			throw new BadCredentialsException("Wrong password");
		}
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), userLoginRequest.getPassword()));
		String jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken, user.getUsername(), user.getId());
	}

}
