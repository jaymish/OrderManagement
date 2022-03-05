package com.order.consumerorder.repo.impl;

import com.order.consumerorder.model.Orders;
import com.order.consumerorder.repo.OrderRepo;
import com.order.consumerorder.service.impl.EmailServiceImpl;
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

    private EmailServiceImpl emailService;

    public OrderRepoImpl(EmailServiceImpl emailService){
        this.emailService=emailService;
    }

    @Override
    public List<Orders> getAllOrders() {
        Query query = entityManager.createQuery("SELECT orders FROM Orders orders");
        List<Orders> ordersList= (List<Orders>) query.getResultList();
        System.out.println("here");
        System.out.println(ordersList);
        return ordersList;
    }

    @Override
    public Orders getOrderById(String orderId) {
        Query query = entityManager.createQuery("SELECT orders FROM Orders orders WHERE orders.id = :orderId ").setParameter("orderId",orderId);
        List<Orders> ordersList = query.getResultList();
        System.out.println("here");
        System.out.println(ordersList);
        if(ordersList.size() == 0 )
            return null;
        return ordersList.get(0);
    }

    @Override
    public List<Orders> getOrderByZip(int zip) {
        Query query = entityManager.createQuery("SELECT orders FROM Orders orders WHERE orders.deliveryMethod.shippingorpickup.zip = :zip ").setParameter("zip",zip);
        List<Orders> ordersList = query.getResultList();
        System.out.println("here");
        System.out.println(ordersList);
        if(ordersList.size() == 0 )
            return null;
        return ordersList;
    }

    @Override
    public Orders orderCancel(String id) {
        Orders orders = getOrderById(id);
        orders.setStatus("Cancel");
        entityManager.merge(orders);
        emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(),"Order Cancled","Thank you your order was cancled. Here is the detail of your order \n"+ orders.toString());
        return orders;
    }

    @Override
    public Orders saveOrder(Orders orders) {
        entityManager.persist(orders);
        emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(),"Order Created","Thank you your order was created. Here is the detail of your order \n"+ orders.toString());
        return orders;
    }
}
