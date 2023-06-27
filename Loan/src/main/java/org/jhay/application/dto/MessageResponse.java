package org.jhay.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponse {
    private Long userId;
    private String message;
}
