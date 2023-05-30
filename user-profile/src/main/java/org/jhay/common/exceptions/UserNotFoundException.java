package org.jhay.common.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super("User with id "+ userId+" does not exist");
    }
}