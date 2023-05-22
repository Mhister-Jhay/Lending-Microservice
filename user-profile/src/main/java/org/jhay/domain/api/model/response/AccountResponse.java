package org.jhay.domain.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long integration;
    private String domain;
    private String subaccount_code;
    private String business_name;
    private String description;
    private String primary_contact_name;
    private String primary_contact_email;
    private String primary_contact_phone;
    private Double percentage_charge;
    private Boolean is_verified;
    private String settlement_bank;
    private String account_number;
    private String settlement_schedule;
    private Boolean active;
    private String currency;
    private Boolean migrate;
    private Integer id;
    private String createdAt;
    private String updatedAt;
}
