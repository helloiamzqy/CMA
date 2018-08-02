package service;

import dao.CustomerDao;
import manager.JmsSender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Customer;
import service.CustomerService;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 1:24 PM 8/2/2018
 * @modified By:
 */
public class CustomerServiceTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void addTest() {
        CustomerService customerService = context.getBean(CustomerService.class);
        Customer customer = new Customer();
        customer.setName("hugo");
        customer.setPassword("123456");
        customerService.addCustomer(customer);
//        customerDao.CheckLoginCustomer();
    }
    @Test
    public void CheckLoginTest(){
        CustomerService customerService = context.getBean(CustomerService.class);
        Customer customer = new Customer();
        customer.setName("hugo");
        customer.setPassword("123456");
        Customer result = customerService.checkLoginCustomer(customer);
        System.out.println(result.getId());
    }

    @Test
    public void upidateTest(){
        CustomerService customerService = context.getBean(CustomerService.class);
        Customer customer = new Customer();
        customer.setName("hugo");
        customer.setPassword("123456");
        Customer result = customerService.checkLoginCustomer(customer);
        result.setPassword("666666");
        Customer result2 = customerService.updateCustomer(result);
        System.out.println(result2.getPassword());
    }
}
