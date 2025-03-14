package com.example.demo.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class ConsumerListener {
    @RabbitListener(queues = "queue-one",concurrency = "5")
    public void consume1(String message){
        System.out.println("consume 1 "+message+" "+Thread.currentThread().getName());
    }
    @RabbitListener(queues = "queue-two")
    public void consume2(String message){
        System.out.println("consume 2 "+message);
    }
}
