package com.alitpc25.twitterclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super();
    }
    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
    public BadCredentialsException(String message) {
        super(message);
    }
}
