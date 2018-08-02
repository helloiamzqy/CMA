package service;

import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;

import java.util.List;

public interface OrderManager {
    public Order addOrder(Order order,String mId,String cId);
    public Order updateOrder(Order order,String mId,String cId);
    public Pager findOrderByMerchant(String mId,String status,int curPage,int pageSize);
    public Pager findOrderByCustomer(String cId, String status,int curPage,int pageSize);
    public Pager findAllOrderByMerchant(String mId, int curPage, int pageSize);
    public Pager findAllOrderByCustomer(String cId,int curPage,int pageSize);
}
