package service.impl;

import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Merchant;
import pojo.Order;
import service.OrderManager;

import java.util.List;

@Service
public class OrderManagerImpl implements OrderManager {
    @Autowired
    private OrderDao orderDao;
    @Transactional
    @Override
    public Order addOrder(Order order) {
        return orderDao.addOrder(order);
    }

    @Transactional
    @Override
    public Order updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Transactional
    @Override
    public List<Order> findOrderByMerchant(Merchant merchant) {
        return null;
    }
}
