package org.jhay.domain.service.notification;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.jhay.application.model.response.MessageResponse;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    private static final Gson GSON = new Gson();
    public void sendAccountMessage(MessageResponse response){
        rabbitTemplate.convertAndSend("userAccountTopic",
                "account.saved",GSON.toJson(response));
    }
    public void sendAddressMessage(MessageResponse response){
        rabbitTemplate.convertAndSend("userAddressTopic",
                "address.saved", GSON.toJson(response));
    }
    public void sendEmploymentMessage(MessageResponse response){
        rabbitTemplate.convertAndSend("userEmploymentTopic",
                "employment.saved",GSON.toJson(response));
    }
}
