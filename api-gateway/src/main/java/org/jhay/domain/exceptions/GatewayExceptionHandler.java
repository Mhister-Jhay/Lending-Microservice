package org.jhay.domain.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.jhay.domain.utils.DateUtils.saveLocalDate;

@RestControllerAdvice
public class GatewayExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .time(saveLocalDate(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExist(UserAlreadyExistException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .time(saveLocalDate(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionResponse> invalidCredentials(InvalidCredentialsException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .time(saveLocalDate(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<ExceptionResponse> userDisabled(UserDisabledException e,
                                                                HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .path(request.getRequestURI())
                .time(saveLocalDate(LocalDateTime.now()))
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
}
