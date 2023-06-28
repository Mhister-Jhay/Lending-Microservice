package org.jhay.application.dto;

import lombok.Builder;
import lombok.Data;
import org.jhay.common.utils.DateUtils;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;

    public ApiResponse(T data){
        this.message = "Request Processed Successfully";
        this.time = DateUtils.saveLocalDate(LocalDateTime.now());
        this.data = data;
    }
}
