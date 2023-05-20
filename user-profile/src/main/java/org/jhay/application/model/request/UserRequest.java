package org.jhay.application.model.request;

import lombok.Builder;
import lombok.Data;
import org.jhay.domain.enums.Gender;

@Data
@Builder
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
}
