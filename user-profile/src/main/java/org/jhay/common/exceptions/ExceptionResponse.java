package org.jhay.common.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private String time;
    private String message;
    private String path;
    private Integer statusCode;
}
