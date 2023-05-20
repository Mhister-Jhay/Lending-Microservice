package org.jhay.domain.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String email) {
        super("User with email "+email+" already exist");
    }
}
