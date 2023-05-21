package org.jhay.domain.api.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveAccountResponse {
    private String message;
    private String accountBank;
    private String accountName;
}
