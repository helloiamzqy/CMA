package service;

import pojo.OrderItem;

import java.util.List;

public interface OrderItemManager {
    public OrderItem addOrderItem(OrderItem orderItem, String oId, String fId);
    public void deleteOrderItemById(String id);
    public List<OrderItem> findOrderItemByOrder(String oId);
}
