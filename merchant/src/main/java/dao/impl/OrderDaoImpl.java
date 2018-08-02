package dao.impl;

import dao.OrderDao;
import org.springframework.stereotype.Repository;
import pojo.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext(name="em")
    private EntityManager manager;
    @Override
    public Order addOrder(Order order) {
        manager.persist(order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Order order1=manager.merge(order);
        return order1;
    }

    @Override
    public Pager findOrderByMerchant(Merchant merchant,String status,int curPage,int pageSize) {
        String jpql="from pojo.Order order where order.merchant=:merchant and order.status=:status";
        Query query = manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        query.setParameter("status",status);
        List<Order> orders=query.getResultList();
        int totalPage=0;
        int totalRow=orders.size();
        if (totalRow%pageSize==0){
            totalPage=totalRow/pageSize;
        }else {
            totalPage=totalRow/pageSize+1;
        }
        orders=query.setFirstResult((curPage-1)*pageSize).setMaxResults(pageSize).getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, orders);
        return pager;
    }

    @Override
    public Pager findOrderByCustomer(Customer customer, String status,int curPage,int pageSize) {
        String jpql="from pojo.Order order where order.customer=:customer and order.status=:status";
        Query query = manager.createQuery(jpql);
        query.setParameter("customer",customer);
        query.setParameter("status",status);
        List<Order> orders=query.getResultList();
        int totalPage=0;
        int totalRow=orders.size();
        if (totalRow%pageSize==0){
            totalPage=totalRow/pageSize;
        }else {
            totalPage=totalRow/pageSize+1;
        }
        orders=query.setFirstResult((curPage-1)*pageSize).setMaxResults(pageSize).getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, orders);
        return pager;
    }

    @Override
    public Pager findAllOrderByCustomer(int curPage, int pageSize,Customer customer) {
        String jpql="from pojo.Order order where order.customer=:customer";
        Query query = manager.createQuery(jpql);
        query.setParameter("customer",customer);
        List<Order> orders=query.getResultList();
        int totalPage=0;
        int totalRow=orders.size();
        if (totalRow%pageSize==0){
            totalPage=totalRow/pageSize;
        }else {
            totalPage=totalRow/pageSize+1;
        }
        orders=query.setFirstResult((curPage-1)*pageSize).setMaxResults(pageSize).getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, orders);
        return pager;
    }

    @Override
    public Pager findAllOrderByMerchant(int curPage, int pageSize,Merchant merchant) {
        String jpql="from pojo.Order order where order.merchant=:merchant";
        Query query = manager.createQuery(jpql);
        query.setParameter("merchant",merchant);
        List<Order> orders=query.getResultList();
        int totalPage=0;
        int totalRow=orders.size();
        if (totalRow%pageSize==0){
            totalPage=totalRow/pageSize;
        }else {
            totalPage=totalRow/pageSize+1;
        }
        orders=query.setFirstResult((curPage-1)*pageSize).setMaxResults(pageSize).getResultList();
        Pager pager = new Pager(curPage, pageSize, totalPage, totalRow, orders);
        return pager;
    }
}
