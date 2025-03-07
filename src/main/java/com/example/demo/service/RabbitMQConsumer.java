package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    @RabbitListener(queues = "ddy01")
    public void receiveMessage(String message) {
        System.out.println("Message Received: " + message);
    }
}
