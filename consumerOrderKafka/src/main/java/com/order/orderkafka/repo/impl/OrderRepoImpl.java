package com.order.orderkafka.repo.impl;

import com.order.orderkafka.model.Orders;
import com.order.orderkafka.model.Updates;
import com.order.orderkafka.repo.OrderRepo;
import com.order.orderkafka.service.impl.EmailServiceImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderRepoImpl implements OrderRepo {

    @PersistenceContext
    private EntityManager entityManager;

    EmailServiceImpl emailService;
    public OrderRepoImpl(EmailServiceImpl emailService){
        this.emailService=emailService;
    }

    @Override
    public Orders getOrderById(String orderId) {
        Query query = entityManager.createQuery("SELECT orders FROM Orders orders WHERE orders.id = :orderId ")
                .setParameter("orderId",orderId);
        List<Orders> ordersList = query.getResultList();
        if(ordersList.size() == 0 )
            return null;
        return ordersList.get(0);
    }

    @Override
    public Orders updateOrderStatus(Updates updates) {
        Orders orders = getOrderById(updates.getId());
        orders.setStatus(updates.getStatus());
        entityManager.merge(orders);
        emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(), "Order "+updates.getStatus(), updates.getId());
        return orders;
    }

    @Override
    public Orders saveOrder(Orders orders) {
        entityManager.persist(orders);
        System.out.println("saved");
        return orders;
    }
}
