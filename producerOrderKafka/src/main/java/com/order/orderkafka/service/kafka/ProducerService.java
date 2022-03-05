package com.order.orderkafka.service.kafka;



import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Slf4j
public class ProducerService {


    private final KafkaTemplate<String, Orders> ordersKafkaTemplate;
    private final KafkaTemplate<String, String> orderTemplate;
    private final KafkaTemplate<String, Updates> updatesKafkaTemplate;


    @Value("${kafka.topic.json.name}")
    private String JSON_TOPIC;

    @Value("${kafka.topic.string.name}")
    private String STRING_TOPIC;

    @Value("${kafka.topic.update.name}")
    private String UPDATE_TOPIC;

    public ProducerService(KafkaTemplate<String, Orders> ordersKafkaTemplate, KafkaTemplate<String, String> orderTemplate, KafkaTemplate<String, Updates> updatesKafkaTemplate) {

        this.ordersKafkaTemplate = ordersKafkaTemplate;
        this.orderTemplate=orderTemplate;
        this.updatesKafkaTemplate=updatesKafkaTemplate;
    }


    public void sendMessage(String id){
        log.info(String.format("$$$$ => Consumed Message: %s", id));

        orderTemplate.executeInTransaction(t -> {
            t.send(STRING_TOPIC, UUID.randomUUID().toString(), id);

            return true;
        });
    }

    public void sendMessageUpdate(Updates updates){
        log.info(String.format("$$$$ => Consumed Message: %s", updates));

        updatesKafkaTemplate.executeInTransaction(t -> {
            t.send(UPDATE_TOPIC, UUID.randomUUID().toString(), updates);

            return true;
        });
    }

    public void sendMessageJson(Orders orders){
        log.info(String.format("$$$$ => Consumed Message: %s", orders));

        ordersKafkaTemplate.executeInTransaction(t -> {
            t.send(JSON_TOPIC, orders.getId(), orders);

            return true;
        });
    }

}
