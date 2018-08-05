package service;

import pojo.Order;
import pojo.Pager;

import java.util.List;

public interface OrderService {
    public Order addOrder(Order order, String mId, String cId);
    public Order updateOrder(Order order);
    public Pager findOrderByMerchant(String mId, String status, int curPage, int pageSize);
    public Pager findOrderByCustomer(String cId, String status, int curPage, int pageSize);
    public Pager findAllOrderByMerchant(String mId, int curPage, int pageSize);
    public Pager findAllOrderByCustomer(String cId, int curPage, int pageSize);
    public Pager findOrderByStatus(String cId,int curPage,int pageSize,String status);
    public void autoSuccessOrder(String time);
    public Order findOrderById(String id);
}
