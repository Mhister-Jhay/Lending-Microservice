package org.jhay.domain.api.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveAccountRequest {
    private String email;
    private String bankCode;
    private String accountNumber;

}
