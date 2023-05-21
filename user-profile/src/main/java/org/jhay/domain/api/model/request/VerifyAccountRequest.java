package org.jhay.domain.api.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyAccountRequest {
    private String account_number;
    private String account_bank;
}
