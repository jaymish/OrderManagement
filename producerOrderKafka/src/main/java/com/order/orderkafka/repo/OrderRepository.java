package com.order.orderkafka.repo;

import com.order.orderkafka.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, String> {
}
