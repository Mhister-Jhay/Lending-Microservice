package org.jhay.application.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressRequest {
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
