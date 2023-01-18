package com.alitpc25.twitterclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	   @ExceptionHandler(value = UserNotFoundException.class)
	   public ResponseEntity<Object> userNotFoundHandler(UserNotFoundException exception) {
	      return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
	   }
	   
	   @ExceptionHandler(value = EmailAlreadyInUseException.class)
	   public ResponseEntity<Object> emailAlreadyInUseHandler(EmailAlreadyInUseException exception) {
	      return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
	   }
	   
	   @ExceptionHandler(value = UsernameAlreadyInUseException.class)
	   public ResponseEntity<Object> usernameAlreadyInUseHandler(UsernameAlreadyInUseException exception) {
	      return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
	   }
	   
	   @ExceptionHandler(value = BadCredentialsException.class)
	   public ResponseEntity<Object> badCredentialsHandler(BadCredentialsException exception) {
	      return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
	   }
	   
}
