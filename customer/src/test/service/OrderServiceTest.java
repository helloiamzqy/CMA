package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Food;
import pojo.Merchant;
import pojo.OrderItem;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 10:58 AM 8/3/2018
 * @modified By:
 */
public class OrderServiceTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void addOrderItem() {
        OrderService orderService = context.getBean(OrderService.class);
        OrderItemService orderItemService = context.getBean(OrderItemService.class);
//        orderService.f
//        orderItemService.addOrderItem();
    }
}
