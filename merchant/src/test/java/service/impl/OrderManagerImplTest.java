package service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import pojo.Pager;
import service.CustomerManager;
import service.MerchantManager;
import service.OrderManager;

import java.util.Date;
import java.util.List;

public class OrderManagerImplTest {
    private static ApplicationContext context;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testAddOrder() throws Exception{
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        MerchantManager merchantManager=context.getBean(MerchantManager.class);
        Merchant merchant=new Merchant();

        merchant.setName("123");
        merchant.setPassword("12eqr12");

//        merchant.setId("8a5e9d3d64f57fad0164f57fb27b0002");
        Customer customer=new Customer();

        customer.setName("213");
        customer.setPassword("erqwr3");
//        customer.setId("8a5e9d3d64f57fad0164f57fb26b0001");
        customerManager.addCustomer(customer);
        merchantManager.addMerchant(merchant);
        Order order=new Order();
        order.setCreateTime(new Date());
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setStatus("1");
        order.setTotalPrice(12.23);
        OrderManager orderManager=context.getBean(OrderManager.class);
        Order order1 = orderManager.addOrder(order,merchant.getId(),customer.getId());
        Assert.assertTrue(order1!=null);

    }

    @Test
    public void testUpdateOrder() throws Exception{
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        MerchantManager merchantManager=context.getBean(MerchantManager.class);
        Merchant merchant=new Merchant();

//        merchant.setName("123");
//        merchant.setPassword("12eqr12");

//        merchant.setId("8a5e9d3d64f630a70164f630ac210001");
//        Customer customer=new Customer();

//        customer.setName("213");
//        customer.setPassword("erqwr3");
//        customer.setId("8a5e9d3d64f630a70164f630abf80000");
//        customerManager.addCustomer(customer);
//        merchantManager.addMerchant(merchant);
        Order order=new Order();
        order.setId("8a5e9d3d64f630a70164f630ac290002");
//        order.setCustomer(customer);
//        order.setMerchant(merchant);
//        order.setStatus("fad");
        order.setCreateTime(new Date());
        order.setTotalPrice(672);
        OrderManager orderManager=context.getBean(OrderManager.class);
        Order order1 = orderManager.updateOrder(order);
        Assert.assertTrue(order1.getStatus().equals("fad"));

    }

//    @Test
//    public  void testFindOrderByMerchant(){
//        OrderManager orderManager=context.getBean(OrderManager.class);
//        List<Order> orders=orderManager.findOrderByMerchant("8a5e9d3c64f84c7f0164f84c845c0001","1");
//        Assert.assertTrue(orders.size()==2);
//
//    }
//    @Test
//    public  void testFindOrderByCustomer(){
//        OrderManager orderManager=context.getBean(OrderManager.class);
//        List<Order> orders=orderManager.findOrderByCustomer("8a5e9d3c64f84c7f0164f84c841e0000","1");
//        Assert.assertTrue(orders.size()==1);
//    }
    @Test
    public  void testFindAllOrderByMerchant(){
        OrderManager orderManager=context.getBean(OrderManager.class);
        Pager pager =orderManager.findAllOrderByMerchant("8a5e9d3c64f84c7f0164f84c845c0001",1,4);
        Assert.assertTrue(pager.getList().size()==2);
    }
    @Test
    public  void testFindAllOrderByCustomer(){
        OrderManager orderManager=context.getBean(OrderManager.class);
        Pager pager=orderManager.findAllOrderByCustomer("8a5e9d3c64f84c7f0164f84c841e0000",1,4);
        Assert.assertTrue(pager.getList().size()==1);
    }
}
