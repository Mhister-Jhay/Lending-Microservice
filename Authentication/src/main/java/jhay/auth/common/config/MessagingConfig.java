package jhay.auth.common.config;

import com.rabbitmq.client.ConnectionFactory;
import jhay.auth.common.event.EmailEventHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    private static final String TOPIC1 = "userLoggedInTopic";
    private static final String QUEUE_NAME1 = "user.loggedIn";
    private static final String TOPIC3 = "emailTopic";
    private static final String QUEUE_NAME3 ="user.email";
    private static final String TOPIC2 = "userRegisteredTopic";
    private static final String QUEUE_NAME2 = "user.registered";

    @Bean
    public Queue userLoggedInQueue(){
        return new Queue(QUEUE_NAME1, false);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(QUEUE_NAME3, false);
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
    public TopicExchange emailTopic(){
        return new TopicExchange(TOPIC3);
    }
    @Bean
    public TopicExchange userRegisteredTopic(){
        return new TopicExchange(TOPIC2);
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        return new ConnectionFactory();
    }

    @Bean
    public Binding userLoggedInBinding(Queue userLoggedInQueue, TopicExchange userLoggedInTopic){
        return BindingBuilder.bind(userLoggedInQueue).to(userLoggedInTopic).with("user.#");
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, TopicExchange emailTopic){
        return BindingBuilder.bind(emailQueue).to(emailTopic).with("user.#");
    }

    @Bean
    public Binding userLoggedOutBinding(Queue userRegisteredQueue, TopicExchange userRegisteredTopic){
        return BindingBuilder.bind(userRegisteredQueue).to(userRegisteredTopic).with("user.#");
    }

    @Bean
    public SimpleMessageListenerContainer emailContainer(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory,
                                                         EmailEventHandler emailEventHandler){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME3);
        container.setMessageListener(new MessageListenerAdapter(emailEventHandler, "handleEmailSending"));
        return container;
    }
}

