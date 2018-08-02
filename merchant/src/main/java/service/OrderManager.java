package service;

import pojo.Customer;
import pojo.Merchant;
import pojo.Order;

import java.util.List;

public interface OrderManager {
    public Order addOrder(Order order,String mId,String cId);
    public Order updateOrder(Order order);
    public List<Order> findOrderByMerchant(String mId,String status);
    public List<Order> findOrderByCustomer(String cId, String status);
    public List<Order> findAllOrderByMerchant(String mId);
    public List<Order> findAllOrderByCustomer(String cId);
}
