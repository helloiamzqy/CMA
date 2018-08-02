package service.impl;

import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import service.OrderManager;

import java.util.List;

@Service
public class OrderManagerImpl implements OrderManager {
    @Autowired
    private OrderDao orderDao;
    @Transactional
    @Override
    public Order addOrder(Order order,String mId,String cId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        order.setMerchant(merchant);
        Customer customer=new Customer();
        customer.setId(cId);
        order.setCustomer(customer);
        return orderDao.addOrder(order);
    }

    @Transactional
    @Override
    public Order updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }

    @Transactional
    @Override
    public List<Order> findOrderByMerchant(String mId,String status) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return orderDao.findOrderByMerchant(merchant,status);
    }

    @Transactional
    @Override
    public List<Order> findOrderByCustomer(String cId, String status) {
        Customer customer=new Customer();
        customer.setId(cId);
        return orderDao.findOrderByCustomer(customer,status);
    }

    @Override
    public List<Order> findAllOrderByMerchant(String mId) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return orderDao.findAllOrderByMerchant(merchant);
    }

    @Override
    public List<Order> findAllOrderByCustomer(String cId) {
        Customer customer=new Customer();
        customer.setId(cId);
        return orderDao.findAllOrderByCustomer(customer);
    }


}
