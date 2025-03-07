package com.example.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String message) {
        String exchange="/";
        String routingKey="message";
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Message Sent: " + message);
    }
}
