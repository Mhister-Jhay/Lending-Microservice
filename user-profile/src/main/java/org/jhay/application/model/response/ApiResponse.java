package org.jhay.application.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import org.jhay.common.utils.DateUtils;

import java.time.LocalDateTime;

@Data
@Builder
@JsonPropertyOrder(value = {"message", "time", "data"})
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;

    public ApiResponse() {
        this.message = "Processed Successfully";
        this.time = DateUtils.saveLocalDate(LocalDateTime.now());
    }

    public ApiResponse(T data) {
        this.message = "Processed Successfully";
        this.time = DateUtils.saveLocalDate(LocalDateTime.now());
        this.data = data;
    }

    public ApiResponse(String message, String time, T data) {
        this.message = message;
        this.time = time;
        this.data = data;
    }
    // Rest of the class...
}
