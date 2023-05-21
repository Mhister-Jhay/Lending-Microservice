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
}
