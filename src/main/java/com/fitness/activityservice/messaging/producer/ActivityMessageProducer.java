package com.fitness.activityservice.messaging.producer;

import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public void sendActivity(Activity activity){
        log.info(activity.toString(), exchangeName, routingKey);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, activity);
    }
}
