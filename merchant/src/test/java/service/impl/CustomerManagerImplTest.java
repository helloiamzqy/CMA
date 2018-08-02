package service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Customer;
import service.CustomerManager;

import java.util.List;

public class CustomerManagerImplTest {
    private static ApplicationContext context;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Test
    public void testAddCustomer(){
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        Customer customer=new Customer();
        customer.setName("af");
        customer.setPassword("23");
        Customer customer1=customerManager.addCustomer(customer);
    }
    @Test
    public void testUpdateCustomer(){
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        Customer customer=new Customer();
        customer.setId("8a5e9d3d64f62d6d0164f62d72e50000");
        customer.setPassword("2131231321");
        customer.setName("nihao");
        customerManager.updateCustomer(customer);
        Assert.assertTrue(customer.getName().equals("nihao"));
    }
    @Test
    public void testFindCustomers(){
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        List<Customer> customers=customerManager.findCustomer();
        Assert.assertTrue(customers.size()==1);
    }
}
