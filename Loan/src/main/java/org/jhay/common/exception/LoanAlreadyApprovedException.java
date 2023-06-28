package org.jhay.common.exception;

public class LoanAlreadyApprovedException extends RuntimeException{
    public LoanAlreadyApprovedException(String message) {
        super(message);
    }
}
