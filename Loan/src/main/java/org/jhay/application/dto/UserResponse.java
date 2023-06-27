package org.jhay.application.dto;

import lombok.Builder;
import lombok.Data;
import org.jhay.domain.enums.Gender;
@Data
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
}
