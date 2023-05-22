package org.jhay.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BankRequest {
    private Long id;
    private String code;
    private String name;
    private String slug;
    private String longcode;
    private String gateway;
    private Boolean pay_with_bank;
    private Boolean active;
    private Boolean is_deleted;
    private String country;
    private String currency;
    private String type;
    private String createdAt;
    private String updatedAt;

}
