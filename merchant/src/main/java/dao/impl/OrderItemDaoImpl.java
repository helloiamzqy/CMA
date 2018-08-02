package dao.impl;

import dao.OrderItemDao;
import org.springframework.stereotype.Repository;
import pojo.Order;
import pojo.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public OrderItem addOrderItem(OrderItem orderItem) {
        manager.persist(orderItem);
        return orderItem;
    }

    @Override
    public void deleteOrderItemById(String id) {
        OrderItem orderItem=manager.getReference(OrderItem.class,id);
        manager.remove(orderItem);
    }

    @Override
    public List<OrderItem> findOrderItemByOrder(Order order) {
        String jpql="from pojo.OrderItem orderItem where orderItem.order=:order";
        Query query=manager.createQuery(jpql);
        query.setParameter("order",order);
        List<OrderItem> orderItems=query.getResultList();
        return orderItems;
    }
}
