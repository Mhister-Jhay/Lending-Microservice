package org.jhay.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String assignedTo;
    private Date validTill;
}
