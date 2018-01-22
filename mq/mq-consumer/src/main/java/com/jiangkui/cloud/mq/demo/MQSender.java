package com.jiangkui.cloud.mq.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ellien
 * @date 2018/01/22 10:16
 */
@Component
public class MQSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.amqpTemplate.convertAndSend("hello", context);
    }
}
