package com.example.demo.service.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String msg) {
            rabbitTemplate.convertAndSend("ddy-fanout","sssss",msg);
    }
}
