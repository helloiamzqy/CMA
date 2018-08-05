package service.impl;

import dao.OrderItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.OrderItem;
import service.OrderItemService;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 10:38 AM 8/3/2018
 * @modified By:
 */

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemDao orderItemDao;

    @Transactional
    @Override
    public void addOrderItem(OrderItem orderItem) {
        System.out.println(orderItem.toString());
        orderItemDao.addOrderItem(orderItem);
    }
}
