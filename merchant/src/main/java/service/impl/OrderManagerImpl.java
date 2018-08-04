package service.impl;

import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;
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
    public Pager findOrderByMerchant(String mId,String status,int curPage,int pageSize) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return orderDao.findOrderByMerchant(merchant,status,curPage,pageSize);
    }

    @Transactional
    @Override
    public  Pager findOrderByCustomer(String cId, String status,int curPage,int pageSize) {
        Customer customer=new Customer();
        customer.setId(cId);
        return orderDao.findOrderByCustomer(customer,status,curPage,pageSize);
    }

    @Transactional
    @Override
    public Pager findAllOrderByMerchant(String mId, int curPage, int pageSize) {
        Merchant merchant=new Merchant();
        merchant.setId(mId);
        return orderDao.findAllOrderByMerchant(curPage,pageSize,merchant);
    }

    @Transactional
    @Override
    public Pager findAllOrderByCustomer(String cId, int curPage, int pageSize) {
        Customer customer=new Customer();
        customer.setId(cId);
        return orderDao.findAllOrderByCustomer(curPage,pageSize,customer);
    }


}
