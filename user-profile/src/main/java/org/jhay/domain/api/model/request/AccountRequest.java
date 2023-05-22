package org.jhay.domain.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequest {
    private String business_name;
    private String settlement_bank;
    private String account_number;
    private Double percentage_charge;
    private String description;
    private String primary_contact_email;
    private String primary_contact_phone;

}
