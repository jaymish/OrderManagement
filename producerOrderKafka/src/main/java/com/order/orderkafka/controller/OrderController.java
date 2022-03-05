package com.order.orderkafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import com.order.orderkafka.service.kafka.ProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/Order")
public class OrderController {

    private ProducerService producerService;



    @Autowired
    public OrderController(ProducerService producerService){
        this.producerService=producerService;
    }


    @PostMapping("/CreateOrder")
    @ApiOperation(value = "Order created by Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Order Created")
    })
    public boolean createOrder(@RequestBody Orders orders) throws JsonProcessingException {
        System.out.println(orders);
        producerService.sendMessageJson(orders);
        return true;
    }

    @PostMapping("/updateorder")
    public boolean updateOrder(@RequestBody Updates updates) throws JsonProcessingException {
        System.out.println(updates);
        producerService.sendMessageUpdate(updates);
        return true;
    }

}
