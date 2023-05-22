package org.jhay.domain.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyAccountRequest {
    private String email;
    private String account_number;
    private String account_bank;
}
