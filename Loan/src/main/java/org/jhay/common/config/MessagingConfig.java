package org.jhay.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MessagingConfig {
    private static final String TOPIC = "userRegisteredTopic";
    private static final String QUEUE_NAME = "user.registered.loan";
    private static final String TOPIC2 = "userAccountTopic";
    private static final String QUEUE_NAME_2 = "account.saved.loan";
    private static final String TOPIC3 = "userAddressTopic";
    private static final String QUEUE_NAME_3 = "address.saved.loan";

    @Bean
    public Queue userRegisteredQueue(){
        return new Queue(QUEUE_NAME,false);
    }
    @Bean
    public TopicExchange userRegisteredTopicExchange(){
        return new TopicExchange(TOPIC);
    }
    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, TopicExchange userRegisteredTopic){
        return BindingBuilder.bind(userRegisteredQueue).to(userRegisteredTopic).with("user.#");
    }
}
