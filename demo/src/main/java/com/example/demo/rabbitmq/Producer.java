package com.example.demo.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

public class Producer {
    private static final String QUEUE_NAME = "ddy01"; // 队列名称

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.196.207.12"); // 替换为你的 RabbitMQ 服务器地址
        factory.setPort(5672);
        factory.setUsername("ddy"); // 替换为你的用户名
        factory.setPassword("ddy1111"); // 替换为你的密码

        // 创建连接
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            Map<String, Object> arguments = new HashMap<>();
            arguments.put("x-max-priority", 100);
            channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);

            String message5 = "Hello, RabbitMQ 5 ";
            AMQP.BasicProperties properties5 = new AMQP.BasicProperties.Builder()
                    .priority(5)
                    .build();
            channel.basicPublish("", QUEUE_NAME, properties5, message5.getBytes());
            System.out.println(" [x] Sent '" + message5 + "'");

            String message1 = "Hello, RabbitMQ 1 ";
            AMQP.BasicProperties properties1 = new AMQP.BasicProperties.Builder()
                    .priority(1)
                    .build();
            channel.basicPublish("", QUEUE_NAME, properties1, message1.getBytes());
            System.out.println(" [x] Sent '" + message1 + "'");

            String message9 = "Hello, RabbitMQ 9 ";
            AMQP.BasicProperties properties9 = new AMQP.BasicProperties.Builder()
                    .priority(9)
                    .build();
            channel.basicPublish("", QUEUE_NAME, properties9, message9.getBytes());
            System.out.println(" [x] Sent '" + message9 + "'");





        }
    }
}