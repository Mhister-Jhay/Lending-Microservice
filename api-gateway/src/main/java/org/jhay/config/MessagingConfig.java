package org.jhay.config;

import org.jhay.event.AuthEventHandler;
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
    private static final String TOPIC1 = "userLoggedInTopic";
    private static final String QUEUE_NAME1 = "user.loggedIn.gateway";
    private static final String TOPIC2 = "userRegisteredTopic";
    private static final String QUEUE_NAME2 = "user.registered.gateway";

    @Bean
    public Queue userLoggedInQueue(){
        return new Queue(QUEUE_NAME1, false);
    }

    @Bean
    public Queue userRegisteredQueue(){
        return new Queue(QUEUE_NAME2, false);
    }

    @Bean
    public TopicExchange userLoggedInTopic(){
        return new TopicExchange(TOPIC1);
    }

    @Bean
    public TopicExchange userRegisteredTopic(){
        return new TopicExchange(TOPIC2);
    }

    @Bean
    public Binding userLoggedInBinding(Queue userLoggedInQueue, TopicExchange userLoggedInTopic){
        return BindingBuilder.bind(userLoggedInQueue).to(userLoggedInTopic).with("user.#");
    }

    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, TopicExchange userRegisteredTopic){
        return BindingBuilder.bind(userRegisteredQueue).to(userRegisteredTopic).with("user.#");
    }

    @Bean
    public SimpleMessageListenerContainer loginContainer(ConnectionFactory connectionFactory,
                                                    AuthEventHandler authEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME1);
        container.setMessageListener(new MessageListenerAdapter(authEventHandler, "handleUserLogin"));
        return container;
    }
    @Bean
    public SimpleMessageListenerContainer registerContainer(ConnectionFactory connectionFactory,
                                                         AuthEventHandler authEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME2);
        container.setMessageListener(new MessageListenerAdapter(authEventHandler, "handleUserRegistration"));
        return container;
    }
}

