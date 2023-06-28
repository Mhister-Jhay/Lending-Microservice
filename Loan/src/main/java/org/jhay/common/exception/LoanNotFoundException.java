package org.jhay.common.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(String message) {
        super(message);
    }
}
