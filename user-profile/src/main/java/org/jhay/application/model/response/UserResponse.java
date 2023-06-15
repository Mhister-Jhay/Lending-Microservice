package org.jhay.application.model.response;

import lombok.Builder;
import lombok.Data;
import org.jhay.domain.enums.Gender;

import java.io.Serializable;

@Data
@Builder
public class UserResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Gender gender;
}
