package org.jhay.domain.api.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyAccountResponse {
    private String account_number;
    private String account_name;
}
