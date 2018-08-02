package service.impl;

import dao.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Order;
import pojo.OrderItem;
import service.OrderItemManager;

import java.util.List;

@Service
public class OrderItemManagerImpl implements OrderItemManager {
    @Autowired
    private OrderItemDao orderItemDao;
    @Transactional
    @Override
    public OrderItem addOrderItem(OrderItem orderItem,String oId) {
        Order order=new Order();
        order.setId(oId);
        orderItem.setOrder(order);
        return orderItemDao.addOrderItem(orderItem);
    }

    @Transactional
    @Override
    public void deleteOrderItemById(String id) {
        orderItemDao.deleteOrderItemById(id);
    }

    @Transactional
    @Override
    public List<OrderItem> findOrderItemByOrder(String oId) {
        Order order=new Order();
        order.setId(oId);
        return orderItemDao.findOrderItemByOrder(order);
    }
}
