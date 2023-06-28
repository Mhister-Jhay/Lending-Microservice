package org.jhay.common.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private String path;
    private Integer statusCode;
    private LocalDateTime time;
}
