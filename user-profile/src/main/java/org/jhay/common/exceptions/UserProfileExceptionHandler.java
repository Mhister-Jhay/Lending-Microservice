package org.jhay.common.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.jhay.common.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserProfileExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAlreadyExist(UserAlreadyExistException e,
                                                              HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFound(UserNotFoundException e,
                                                              HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userAddressExist(AddressAlreadyExistException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userAddressNotFound(AddressNotFoundException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmploymentAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> userEmploymentExist(EmploymentAlreadyExistException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmploymentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userEmploymentNotFound(EmploymentNotFoundException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> userNotAuthorized(UnauthorizedException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
    }
}
