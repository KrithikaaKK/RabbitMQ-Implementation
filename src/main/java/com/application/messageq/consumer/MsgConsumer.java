package com.application.messageq.consumer;

import com.application.messageq.config.MsgConfiguration;
import com.application.messageq.dto.Dept;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MsgConsumer {
    //@RabbitListener(queues = "demo")
    public void consumeMsg(@Payload Dept dept) {
        System.out.println("Message " + dept );
    }
}
