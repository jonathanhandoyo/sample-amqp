package com.jonathan.listener;

import com.jonathan.domain.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomRabbitListener {

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "test-exchange", type = ExchangeTypes.TOPIC, durable = "true"),
                    value = @Queue(value = "test-queue-1", durable = "true"),
                    key = "#"
            )
    )
    public void listen1(Notification notification, Message message) {
        log.info(message.toString());
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "test-exchange", type = ExchangeTypes.TOPIC, durable = "true"),
                    value = @Queue(value = "test-queue-2", durable = "true"),
                    key = "any"
            )
    )
    public void listen2(Notification notification, Message message) {
        log.info(message.toString());
    }
}
