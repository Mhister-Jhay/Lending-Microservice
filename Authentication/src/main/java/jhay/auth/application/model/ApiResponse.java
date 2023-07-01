package jhay.auth.application.model;

import jhay.auth.common.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
