package org.jhay.domain.service.notification;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationService {
    private final RabbitTemplate rabbitTemplate;
    public void sendAccountMessage(String response){
        rabbitTemplate.convertAndSend("userAccountTopic",
                "account.saved",response);
    }

    public void sendAddressMessage(String response){
        rabbitTemplate.convertAndSend("userAddressTopic",
                "address.saved", response);
    }
    public void sendEmploymentMessage(String response){
        rabbitTemplate.convertAndSend("userEmploymentTopic",
                "employment.saved", response);
    }
}
