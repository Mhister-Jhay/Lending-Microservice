package org.jhay.domain.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyAccountResponse {
    private Integer bank_id;
    private String account_number;
    private String account_name;
}
