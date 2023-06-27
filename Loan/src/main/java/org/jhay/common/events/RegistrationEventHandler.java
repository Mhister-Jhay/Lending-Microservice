package org.jhay.common.events;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.jhay.application.dto.UserResponse;
import org.jhay.domain.model.User;
import org.jhay.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationEventHandler {
    private static final Gson GSON = new Gson();
    private final UserRepository userRepository;

    public void handleUserRegistration(String userDetails) {
        UserResponse userResponse = GSON.fromJson(userDetails, UserResponse.class);
        if (userRepository.existsByEmail(userResponse.getEmail())) {
            System.out.println("User Already Exists");
        } else {
            userRepository.save(User.builder()
                    .email(userResponse.getEmail())
                    .lastName(userResponse.getLastName())
                    .firstName(userResponse.getFirstName())
                    .phoneNumber(userResponse.getPhoneNumber())
                    .gender(userResponse.getGender())
                    .isAddressSaved(false)
                    .isEmploymentSaved(false)
                    .isAccountSaved(false)
                    .build());
            System.out.println("User Registered Successfully");
        }
    }
}
