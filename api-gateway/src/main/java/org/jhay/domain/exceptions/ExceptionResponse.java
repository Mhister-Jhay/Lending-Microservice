package org.jhay.domain.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private String message;
    private String time;
    private String path;
    private Integer statusCode;
}
