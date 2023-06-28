package org.jhay.common.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.jhay.application.model.response.ApiResponse;
import org.jhay.common.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserProfileExceptionHandler {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userAlreadyExist(UserAlreadyExistException e,
                                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userNotFound(UserNotFoundException e,
                                                              HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressAlreadyExistException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userAddressExist(AddressAlreadyExistException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userAddressNotFound(AddressNotFoundException e,
                                                          HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmploymentAlreadyExistException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userEmploymentExist(EmploymentAlreadyExistException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmploymentNotFoundException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userEmploymentNotFound(EmploymentNotFoundException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<ExceptionResponse>> userNotAuthorized(UnauthorizedException e,
                                                                 HttpServletRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .time(DateUtils.saveLocalDate(LocalDateTime.now()))
                .message(e.getMessage())
                .path(request.getRequestURI())
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
        ApiResponse<ExceptionResponse> apiResponse = new ApiResponse<>(exceptionResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.UNAUTHORIZED);
    }
}
