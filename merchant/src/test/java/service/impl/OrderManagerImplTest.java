package service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import service.CustomerManager;
import service.MerchantManager;
import service.OrderManager;

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
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setStatus("1");
        order.setTotalPrice(12.23);
        OrderManager orderManager=context.getBean(OrderManager.class);
        Order order1 = orderManager.addOrder(order);
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
        order.setTotalPrice(672);
        OrderManager orderManager=context.getBean(OrderManager.class);
        Order order1 = orderManager.updateOrder(order);
        Assert.assertTrue(order1.getStatus().equals("fad"));

    }

    @Test
    public  void testFindOrderByMerchant(){

    }
}
