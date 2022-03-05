package com.order.consumerorder.service.impl;


import com.order.consumerorder.model.Orders;
import com.order.consumerorder.repo.OrderRepo;
import com.order.consumerorder.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    private OrderRepo orderRepo;


    public DefaultOrderService(OrderRepo orderRepo){
        this.orderRepo = orderRepo;
    }
    @Override
    public boolean createOrder(Orders orders) {
        System.out.println(orders);
        orderRepo.saveOrder(orders);
        return true;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = orderRepo.getAllOrders();
        return ordersList;
    }

    @Override
    public Orders getOrdersById(String id){
        Orders orders = orderRepo.getOrderById(id);
        return orders;
    }

    @Override
    public List<Orders> getOrdersByZip(int zip){
        List<Orders> ordersList = orderRepo.getOrderByZip(zip);
        return ordersList;
    }

    @Override
    public Orders cancelOrder(String id) {
        return orderRepo.orderCancel(id);
    }

}
