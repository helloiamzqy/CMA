package dao;

import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;

import java.util.List;

public interface OrderDao {
    public Order addOrder(Order order);
    public Order updateOrder(Order order);
    public Pager findOrderByMerchant(Merchant merchant, String status, int curPage, int pageSize);
    public Pager findOrderByCustomer(Customer customer, String status, int curPage, int pageSize);
    public Pager findAllOrderByCustomer(int curPage, int pageSize, Customer customer);
    public Pager findAllOrderByMerchant(int curPage, int pageSize, Merchant merchant);
    public Pager findOrderByStatus(Customer customer, List<String> status, int curPage, int pageSize);
    public void autoSuccessOrder(String time);
    public Order findOrderById(String id);
}
