package dao;

import pojo.Order;
import pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public OrderItem addOrderItem(OrderItem orderItem);

    public void deleteOrderItemById(String id);

    public List<OrderItem> findOrderItemByOrder(Order order);
}
