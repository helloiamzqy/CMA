package service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import pojo.Food;
import pojo.Order;
import pojo.OrderItem;
import service.FoodManger;
import service.OrderItemManager;
import service.OrderManager;

import java.util.List;

public class OrderItemManagerTest {
    private static ApplicationContext context;
    private FoodManger foodManger;
    private OrderItemManager orderItemManager;
    private OrderManager orderManager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void initManager(){
        foodManger=context.getBean(FoodManger.class);
        orderItemManager=context.getBean(OrderItemManager.class);
        orderManager=context.getBean(OrderManager.class);
    }
    @Test
    public void testAddOrderItem(){
        OrderItem orderItem=new OrderItem();
        Food food=new Food();
        food.setFoodName("fdadas");
        food.setPrice(12321);
        food.setPicture("fdafds");
//        foodManger.addFood(food);
        orderItem.setFoodNum(1);
        orderItem.setFood(food);
        orderItem.setTotalPrice(666);
//        OrderItem orderItem1= orderItemManager.addOrderItem(orderItem,"8a5e9d3d64f9eed50164f9eedabf0002");
//        Assert.assertTrue(orderItem1!=null);


    }
    @Test
    public void testDeleteOrderItem(){
        OrderItemManager manager=context.getBean(OrderItemManager.class);
        manager.deleteOrderItemById("8a5e9d3d64f862c70164f862cca50000");
    }
    @Test
    public void testFindOrderItemByOrder(){
        OrderItemManager manager=context.getBean(OrderItemManager.class);
        List<OrderItem> orderItems=manager.findOrderItemByOrder("8a5e9d3c64f84c7f0164f84c846c0002");
        for (OrderItem orderItem:orderItems){
            System.out.println(orderItem.toString());
        }
    }
}
