package dao.impl;

import dao.OrderDao;
import org.springframework.stereotype.Repository;
import pojo.Merchant;
import pojo.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public List<Order> findOrderByMerchant(Merchant merchant) {
        return null;
    }
}
