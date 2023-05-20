package jhay.auth.domain.service.registration;
import jakarta.servlet.http.HttpServletRequest;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.application.model.RegistrationRequest;
import jhay.auth.application.model.UserResponse;
import jhay.auth.common.event.RegistrationEvent;
import jhay.auth.common.security.jwt.JwtAuthProvider;
import jhay.auth.common.security.jwt.JwtToken;
import jhay.auth.common.security.jwt.JwtTokenRepository;
import jhay.auth.common.utils.EmailUtils;
import jhay.auth.domain.model.Role;
import jhay.auth.domain.model.User;
import jhay.auth.domain.service.notification.NotificationService;
import jhay.auth.domain.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImpl userService;
    private final JwtAuthProvider authProvider;
    private final NotificationService notificationService;
    private final JwtTokenRepository jwtTokenRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public String registerUser(RegistrationRequest registerRequest, HttpServletRequest request){
        userService.verifyUserExistence(registerRequest.getEmail());
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .isEnabled(true)
                .isLocked(false)
                .build();
        User theUser = userService.saveUser(user);
        notificationService.sendRegisteredMessage(UserResponse.builder()
                        .id(theUser.getId())
                        .firstName(theUser.getFirstName())
                        .lastName(theUser.getLastName())
                        .email(theUser.getEmail())
                        .build());
        publisher.publishEvent(new RegistrationEvent(user, EmailUtils.applicationUrl(request)));
        return "Registration Successful, Please Check Your Mail for Verification Link";
    }
}
