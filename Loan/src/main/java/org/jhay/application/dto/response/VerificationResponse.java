package org.jhay.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VerificationResponse {
    private Long id;
    private String domain;
    private String status;
    private String reference;
    private Double amount;
    private String message;
    private String gateway_response;
    private LocalDate paid_at;
    private LocalDate created_at;
    private String channel;
    private String currency;
    private String ip_address;
    private String metadata;
    private LogResponse log;
    private Integer fees;
    private String fees_split;
    private AuthorizationResponse authorization;
}
