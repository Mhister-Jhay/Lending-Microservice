package jhay.auth.domain.service.notification;

import com.google.gson.Gson;
import jhay.auth.application.model.AuthResponse;
import jhay.auth.application.model.UserResponse;
import jhay.auth.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    private static final Gson GSON = new Gson();
    public void sendLoginMessage(AuthResponse authResponse){
        rabbitTemplate.convertAndSend("userLoggedInTopic",
                "user.loggedIn",GSON.toJson(authResponse));
    }
    public void sendRegisteredMessage(UserResponse userResponse){
        rabbitTemplate.convertAndSend("userRegisteredTopic",
                "user.registered", GSON.toJson(userResponse));
    }
    public void sendEmailMessage(User user){
        rabbitTemplate.convertAndSend("emailTopic",
                "user.email", GSON.toJson(user));
    }
}
