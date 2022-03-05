package com.order.orderkafka.service.impl;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import com.order.orderkafka.repo.OrderRepo;
import com.order.orderkafka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private OrderRepo orderRepo;

    @Autowired
    public DefaultOrderService( RestTemplate restTemplate, ObjectMapper objectMapper, OrderRepo orderRepo){
        this.restTemplate=restTemplate;
        this.objectMapper=objectMapper;
        this.orderRepo=orderRepo;
    }

    @Override
    public boolean addWeatherReadings(Orders orders) throws JsonProcessingException {
        orderRepo.saveOrder(orders);
        return true;
    }

    @Override
    public Orders updateOrder(Updates updates) {
        return orderRepo.updateOrderStatus(updates);
    }
}
