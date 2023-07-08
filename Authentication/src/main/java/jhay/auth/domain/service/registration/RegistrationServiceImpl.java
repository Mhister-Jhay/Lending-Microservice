package jhay.auth.domain.service.registration;

import jhay.auth.application.model.RegistrationRequest;
import jhay.auth.application.model.UserResponse;
import jhay.auth.common.event.RegistrationEvent;
import jhay.auth.domain.enums.Gender;
import jhay.auth.domain.enums.Role;
import jhay.auth.domain.enums.UserType;
import jhay.auth.domain.model.User;
import jhay.auth.domain.service.notification.NotificationService;
import jhay.auth.domain.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final NotificationService notificationService;
    private final ApplicationEventPublisher publisher;

    @Override
    public String registerUser(RegistrationRequest registerRequest){
        userService.verifyUserExistence(registerRequest.getEmail());
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .gender(Gender.valueOf(registerRequest.getGender().toUpperCase()))
                .type(UserType.valueOf(registerRequest.getActiveChoice().toUpperCase()))
                .isEnabled(false)
                .isLocked(false)
                .build();
        User theUser = userService.saveUser(user);
        notificationService.sendRegisteredMessage(UserResponse.builder()
                        .id(theUser.getId())
                        .firstName(theUser.getFirstName())
                        .lastName(theUser.getLastName())
                        .email(theUser.getEmail())
                        .phoneNumber(theUser.getPhoneNumber())
                        .gender(theUser.getGender())
                        .build());
//        notificationService.sendEmailMessage(theUser);
        publisher.publishEvent(new RegistrationEvent(theUser));
        return "Registration Successful, Please Check Your Mail for Verification Link";
    }
}
