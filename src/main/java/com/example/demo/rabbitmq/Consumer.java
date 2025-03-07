package com.example.demo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private static final String QUEUE_NAME = "ddy01";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.196.207.12"); // 替换为你的 RabbitMQ 服务器地址
        factory.setPort(5672);        // 如果使用默认端口，可以省略这行
        factory.setUsername("ddy"); // 替换为你的用户名
        factory.setPassword("ddy1111"); // 替换为你的密码

        // 创建连接
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // 设置每次只接收一条消息
            channel.basicQos(1);

            // 创建消费者
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received '" + message + "'");

//                try {
//                    // 处理消息
//                    // 如果处理成功
//                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//                    System.out.println("处理成功");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // 如果处理失败，可以选择重新入队或丢弃消息
//                    //channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true); // 重新入队
//                    // 或者
//                    // channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false); // 丢弃消息
//                    System.out.println("处理异常");
//                }
            };

            // 开始消费
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        }
    }
}