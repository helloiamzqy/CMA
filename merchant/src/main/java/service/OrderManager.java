package service;

import pojo.Customer;
import pojo.Merchant;
import pojo.Order;

import java.util.List;

public interface OrderManager {
    public Order addOrder(Order order);
    public Order updateOrder(Order order);
    public List<Order> findOrderByMerchant(Merchant merchant,String status);
    public List<Order> findOrderByCustomer(Customer customer, String status);
}
