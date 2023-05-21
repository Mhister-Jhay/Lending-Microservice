package org.jhay.domain.api.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    private String account_number;
    private String bank_name;
    private String full_name;
}
