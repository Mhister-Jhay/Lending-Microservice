package org.jhay.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.jhay.application.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userNotFound(UserNotFoundException e,
                                                                      HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProfileNotCompleteException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> profileNotComplete(ProfileNotCompleteException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> unauthorized(UnauthorizedException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .time(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> loanNotFound(LoanNotFoundException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(LoanAlreadyApprovedException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userNotFound(LoanAlreadyApprovedException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.CONFLICT.value())
                .time(LocalDateTime.now())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }
}
