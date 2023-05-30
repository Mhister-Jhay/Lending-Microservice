package org.jhay.application.model.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    private Long id;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private UserResponse userResponse;

}
