package com.jiangkui.cloud.mq.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ellien
 * @date 2018/01/22 10:18
 */
@Component
@RabbitListener(queues = "hello")
public class MQReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }
}
