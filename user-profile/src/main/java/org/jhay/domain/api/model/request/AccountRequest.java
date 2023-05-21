package org.jhay.domain.api.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {
    private String account_bank;
    private String account_number;
    private String business_name;
    private String business_email;
    private String business_mobile;
    private String split_type;
    private Double split_value;
    private String country;

}
