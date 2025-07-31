package com.application.messageq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class MessageQApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageQApplication.class, args);
    }

}
