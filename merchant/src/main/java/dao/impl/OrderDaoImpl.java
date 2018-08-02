package dao.impl;

import dao.OrderDao;
import org.springframework.stereotype.Repository;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public Order addOrder(Order order) {
        manager.persist(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Order order1=manager.merge(order);
        return order1;
    }

    @Override
    public List<Order> findOrderByMerchant(Merchant merchant,String status) {
        String jpql="from pojo.Order order where order.merchant=:merchant and order.status=:status";
        Query query = manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        query.setParameter("status",status);
        List<Order> orders=query.getResultList();
        return orders;
    }

    @Override
    public List<Order> findOrderByCustomer(Customer customer, String status) {
        String jpql="from pojo.Order order where order.customer=:customer and order.status=:status";
        Query query = manager.createQuery(jpql);
        query.setParameter("customer",customer);
        query.setParameter("status",status);
        List<Order> orders=query.getResultList();
        return orders;
    }
}
