package org.jhay.application.model.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse implements Serializable {
    private Long id;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private UserResponse userResponse;

}
