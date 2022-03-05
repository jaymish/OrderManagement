package com.order.orderkafka.repo;


import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;

import java.util.List;


public interface OrderRepo{
    Orders getOrderById(String orderId);
    Orders saveOrder(Orders orders);
    Orders updateOrderStatus(Updates updates);
}
