package org.jhay.common.config;

import lombok.RequiredArgsConstructor;
import org.jhay.common.events.AccountEventHandler;
import org.jhay.common.events.AddressEventHandler;
import org.jhay.common.events.EmploymentEventHandler;
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
@RequiredArgsConstructor
public class MessagingConfig {
    private static final String TOPIC = "userRegisteredTopic";
    private static final String QUEUE_NAME = "user.registered.loan";
    private static final String TOPIC2 = "userAccountTopic";
    private static final String QUEUE_NAME_2 = "account.saved.loan";
    private static final String TOPIC3 = "userAddressTopic";
    private static final String QUEUE_NAME_3 = "address.saved.loan";
    private static final String TOPIC4 = "userEmploymentTopic";
    private static final String QUEUE_NAME_4 = "employment.saved.loan";

    @Bean
    public Queue userRegisteredQueue(){
        return new Queue(QUEUE_NAME,false);
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
    public Queue employmentSavedQueue(){
        return new Queue(QUEUE_NAME_4, false);
    }
    @Bean
    public TopicExchange userRegisteredTopicExchange(){
        return new TopicExchange(TOPIC);
    }
    @Bean
    public TopicExchange accountSavedTopicExchange(){
        return new TopicExchange(TOPIC2);
    }
    @Bean
    public TopicExchange addressSavedTopicExchange() {
        return new TopicExchange(TOPIC3);
    }
    @Bean
    public TopicExchange employmentSavedTopicExchange(){
        return new TopicExchange(TOPIC4);
    }
    @Bean
    public Binding userRegisteredBinding(Queue userRegisteredQueue, TopicExchange userRegisteredTopicExchange){
        return BindingBuilder.bind(userRegisteredQueue).to(userRegisteredTopicExchange).with("user.#");
    }
    @Bean
    public Binding accountSavedBinding(Queue accountSavedQueue, TopicExchange accountSavedTopicExchange){
        return BindingBuilder.bind(accountSavedQueue).to(accountSavedTopicExchange).with("account.#");
    }
    @Bean
    public Binding addressSavedBinding(Queue addressSavedQueue, TopicExchange addressSavedTopicExchange){
        return BindingBuilder.bind(addressSavedQueue).to(addressSavedTopicExchange).with("address.#");
    }
    @Bean
    public Binding employmentSavedBinding(Queue employmentSavedQueue, TopicExchange employmentSavedTopicExchange){
        return BindingBuilder.bind(employmentSavedQueue).to(employmentSavedTopicExchange).with("employment.#");
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
    @Bean
    public SimpleMessageListenerContainer accountContainer(ConnectionFactory connectionFactory,
                                                            AccountEventHandler accountEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_2);
        container.setMessageListener(new MessageListenerAdapter(accountEventHandler, "handleAccountSaved"));
        return container;
    }
    @Bean
    public SimpleMessageListenerContainer addressContainer(ConnectionFactory connectionFactory,
                                                           AddressEventHandler addressEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_3);
        container.setMessageListener(new MessageListenerAdapter(addressEventHandler, "handleAddressSaved"));
        return container;
    }
    @Bean
    public SimpleMessageListenerContainer employmentContainer(ConnectionFactory connectionFactory,
                                                           EmploymentEventHandler employmentEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_4);
        container.setMessageListener(new MessageListenerAdapter(employmentEventHandler, "handleEmploymentSaved"));
        return container;
    }
}
