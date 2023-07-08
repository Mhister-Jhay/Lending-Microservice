package jhay.auth.application.model;

import jakarta.validation.constraints.*;
import jhay.auth.domain.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {
    @NotBlank(message = "Firstname must not be blank")
    private String firstName;
    @NotBlank(message = "Lastname must not be blank")
    private String lastName;
    @NotBlank(message = "Email must not be empty")
    @Email(message = "Must be a valid email")
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Password must not be blank")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*=/,.:;|?])(?=.*[0-9]).{8,}$", message = "Password must be a strong password")
    private String password;
    @NotBlank(message = "Phone Number must not be blank")
    @Size(min = 11, max = 11, message = "Phone number must be 11 digits")
    private String phoneNumber;
    @NotBlank(message = "Gender must not be blank")
    private String gender;
    @NotBlank(message = "User-type must not be blank")
    private String activeChoice;

}
