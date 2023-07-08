package jhay.auth.common.event;

import com.google.gson.Gson;
import jhay.auth.common.exception.UserNotFoundException;
import jhay.auth.domain.model.User;
import jhay.auth.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailEventHandler {
    private static final Gson GSON = new Gson();
    private final ApplicationEventPublisher publisher;
    private final UserRepository userRepository;

    public void handleEmailSending(String userDetails){
        System.out.println("The user details: "+ userDetails);
        User user = GSON.fromJson(userDetails, User.class);
        System.out.println("The User = "+user);
        User theUser = userRepository.findByEmail(user.getEmail())
                        .orElseThrow(() -> new UserNotFoundException("User Does not exist"));
        publisher.publishEvent(new RegistrationEvent(theUser));
        System.out.println("Email Sent Successfully");
    }
}
