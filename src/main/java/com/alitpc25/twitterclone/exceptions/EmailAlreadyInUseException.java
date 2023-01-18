package com.alitpc25.twitterclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException() {
        super();
    }
    public EmailAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
