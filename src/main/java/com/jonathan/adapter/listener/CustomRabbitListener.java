package com.jonathan.adapter.listener;

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
                    exchange = @Exchange(value = "test-exchange-fanout", type = ExchangeTypes.FANOUT, durable = "true"),
                    value = @Queue(value = "test-fanout-1", durable = "true")
            )
    )
    public void listen1(Notification notification, Message message) {
        log.info("incoming: {}", message.toString());
    }
}