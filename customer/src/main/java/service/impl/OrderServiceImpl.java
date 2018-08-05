package service.impl;

import dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;
import pojo.enums.OrderStatusEnum;
import service.OrderService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Transactional
    @Override
    public Order addOrder(Order order, String mId, String cId) {
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

    @Transactional
    @Override
    public Pager findOrderByStatus(String cId, int curPage, int pageSize, String status) {
        //all,finished,unreceive,doing
        List<String> statuss=new ArrayList<>();
        Customer customer=new Customer();
        customer.setId(cId);
        if (status == null||status.equals("all")) {
            return orderDao.findAllOrderByCustomer(curPage,pageSize,customer);
        } else if(status != null&&status.equals("finished")){
            statuss.add(OrderStatusEnum.CANCLE);
            statuss.add(OrderStatusEnum.COMPLETE);
            statuss.add(OrderStatusEnum.REFUSE);
            statuss.add(OrderStatusEnum.COMMENT);
        }else if(status != null&&status.equals("unreceive")){
            statuss.add(OrderStatusEnum.WATING);
        }else if(status != null&&status.equals("doing")){
            statuss.add(OrderStatusEnum.DISPATCHING);
            statuss.add(OrderStatusEnum.RECEIVE);
        }
        return orderDao.findOrderByStatus(customer,statuss,curPage,pageSize);
    }

    @Transactional
    @Override
    public void autoSuccessOrder(String time) {
        orderDao.autoSuccessOrder(time);
    }


}
