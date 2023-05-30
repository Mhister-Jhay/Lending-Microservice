package org.jhay.common.exceptions;

public class AddressAlreadyExistException extends RuntimeException{
    public AddressAlreadyExistException(String message) {
        super(message);
    }
}
