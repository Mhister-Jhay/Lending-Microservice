package org.jhay.common.config;

import org.jhay.common.events.RegistrationEventHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final String TOPIC = "userRegisteredTopic";
    private static final String QUEUE_NAME = "user.registered.profile";
    private static final String TOPIC2 = "userAccountTopic";
    private static final String QUEUE_NAME_2 = "account.saved";
    private static final String TOPIC3 = "userAddressTopic";
    private static final String QUEUE_NAME_3 = "address.saved";
    @Bean
    public Queue userRegisteredQueue(){
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue accountSavedQueue(){
        return new Queue(QUEUE_NAME_2, false);
    }
    @Bean
    public Queue addressSavedQueue(){
        return new Queue(QUEUE_NAME_3, false);
    }
    @Bean
    public TopicExchange accountSavedTopic(){
        return new TopicExchange(TOPIC2);
    }
    @Bean
    public TopicExchange addressSavedTopic(){
        return new TopicExchange(TOPIC3);
    }

    @Bean
    public com.rabbitmq.client.ConnectionFactory connectionFactory(){
        return new com.rabbitmq.client.ConnectionFactory();
    }

    @Bean
    public Binding accountSavedBinding(Queue accountSavedQueue, TopicExchange accountSavedTopic){
        return BindingBuilder.bind(accountSavedQueue).to(accountSavedTopic).with("account.#");
    }
    @Bean
    public Binding addressSavedBinding(Queue addressSavedQueue,TopicExchange addressSavedTopic){
        return BindingBuilder.bind(addressSavedQueue).to(addressSavedTopic).with("address.#");
    }
    @Bean
    public TopicExchange userRegisteredTopic(){
        return new TopicExchange(TOPIC);
    }
    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, TopicExchange userRegisteredTopic){
        return BindingBuilder.bind(userRegisteredQueue).to(userRegisteredTopic).with("user.#");
    }
    @Bean
    public SimpleMessageListenerContainer registerContainer(ConnectionFactory connectionFactory,
                                                         RegistrationEventHandler registrationEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(new MessageListenerAdapter(registrationEventHandler, "handleUserRegistration"));
        return container;
    }
}

