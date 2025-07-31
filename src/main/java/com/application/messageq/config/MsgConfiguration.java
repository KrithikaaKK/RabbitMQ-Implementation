package com.application.messageq.config;
import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MsgConfiguration {
    @Value("${queue.name}")
    public String message;
     public static final String Exchange = "exchange";

  public static final String key = "routing key";
  @Bean
    public Queue queue(){
      Map<String,Object> args = new HashMap<>();
      args.put("x-max-priority",10);
      return new Queue(message,false,false,false,args);
  }
  @Bean
    public TopicExchange exchange(){
      return new TopicExchange(Exchange);
  }
  @Bean
    public Binding binding(Queue queue , TopicExchange exchange){
      return BindingBuilder.bind(queue).to(exchange).with(key);
  }

  @Bean
    public MessageConverter converter(){
      return new Jackson2JsonMessageConverter();
  }

  @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       rabbitTemplate.setMessageConverter(converter());
       return rabbitTemplate;
  }

}
