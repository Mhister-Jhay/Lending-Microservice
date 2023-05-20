package org.jhay.domain.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String message) {
        super("User with details "+message+" already exists");
    }
}
