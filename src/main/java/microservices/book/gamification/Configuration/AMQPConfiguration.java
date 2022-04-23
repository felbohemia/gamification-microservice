package microservices.book.gamification.Configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration {
    /*
       This bean is needed during the declaration the Exchange on the message broker
     */
    @Bean
    TopicExchange topicExchange(@Value("${amqp.exchange.topic}") final String topicName){
        return ExchangeBuilder.topicExchange(topicName).durable(true).build();
    }
    /*
      This bean is needed during the declaration of the Queue on the message broker
    */
    @Bean
    Queue queue(@Value("${amqp.gamification.queue}") final String queueName){
        return QueueBuilder.durable(queueName).build();
    }
    /*
       This bean is needed during the declaration of the Binding on the message broker
     */
    @Bean
    Binding binding(final TopicExchange exchange, final Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("attempt.correct");
    }
    /*
        MessageHandlerMethodFactory is a factory(class for creating objects)
        commonly use for processing incoming messages once they have been received by the
        selected message listener.
        the MessageHandlerMethodFactory has within itself a method that can be used to set the
        message converter it should use for converting messages from json to pojo(plain old java object)
     */
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        /*
            Extends the functionality of the objectmapper by
            add an extension(an object which adds to the functionality,ie, the list
            of things an application can naturally do).
            The ParamterNamesModule ensures that during the call of a creator method(a constructor for example)
            with arguments, that the values obtained from the incoming json object should be ones whose properties
            on the incoming object matches the paramter names in the creator...
            Its means properties not found as parameters on the creator's header are ignored without throwing and
            error.
         */
        converter.getObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        messageHandlerMethodFactory.setMessageConverter(converter);
        return messageHandlerMethodFactory;
    }
    /*
       RabbitListenerConfigurer, a class which configures the rabbitlistener interface
       in this case it tells the rabbitlistener to use the injected MessageHandleMethodFactory from our bean
       which uses mappingjackson2messageconverter as the default message converter.
     */
    @Bean
    RabbitListenerConfigurer listenerConfigurer(final MessageHandlerMethodFactory messageHandlerMethodFactory){
        return (c) ->c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}
