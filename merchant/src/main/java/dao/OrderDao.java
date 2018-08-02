package dao;

import pojo.Merchant;
import pojo.Order;

import java.util.List;

public interface OrderDao {
    public Order addOrder(Order order);
    public Order updateOrder(Order order);
    public List<Order> findOrderByMerchant(Merchant merchant);
}
