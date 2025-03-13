package com.example.demo.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerListener {
    @RabbitListener(queues = "ddy01")
    public void consume1(String message){
        System.out.println("consume1 "+message);
    }
    @RabbitListener(queues = "ddy01")
    public void consume2(String message){
        System.out.println("consume2 "+message);
    }
    @RabbitListener(queues = "ddy01")
    public void consume3(String message){
        System.out.println("consume3 "+message);
    }
}
