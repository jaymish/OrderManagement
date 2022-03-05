package com.order.orderkafka.service.kafka;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import com.order.orderkafka.service.impl.DefaultOrderService;
import com.order.orderkafka.service.impl.EmailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    EmailServiceImpl emailService;
    DefaultOrderService defaultOrderService;

    public ConsumerService(EmailServiceImpl emailService, DefaultOrderService defaultOrderService){
        this.emailService=emailService;
        this.defaultOrderService=defaultOrderService;
    }

    @KafkaListener(containerFactory = "jsonKafkaListenerContainerFactory",
            topics = "${kafka.topic.json.name}",
            groupId = "${kafka.topic.json.groupId}")
    public void consumeCustomerData(Orders orders) throws JsonProcessingException {
        log.info("Consumed Message: {}, {}", orders.getId(), orders);
        System.out.println("Email Notification:"+ orders.getId() +orders+orders);
        defaultOrderService.addWeatherReadings(orders);
        emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(), "Weather", orders.toString());
    }

    @KafkaListener(containerFactory = "updateKafkaListenerContainerFactory",
            topics = "${kafka.topic.update.name}",
            groupId = "${kafka.topic.update.groupId}")
    public void consumeString(Updates updates) throws JsonProcessingException {
        log.info("Consumed Message: {}", updates);
        System.out.println("Email Notification:"+ updates);
        defaultOrderService.updateOrder(updates);
        //emailService.sendSimpleMessage("jaymish9558@gmail.com", "Order Cancel", id);
    }
}
