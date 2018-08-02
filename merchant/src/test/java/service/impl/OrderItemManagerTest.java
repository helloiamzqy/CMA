package service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import pojo.Food;
import pojo.OrderItem;
import service.OrderItemManager;

import java.util.List;

public class OrderItemManagerTest {
    private static ApplicationContext context;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testAddOrderItem(){
        OrderItemManager manager=context.getBean(OrderItemManager.class);
        OrderItem orderItem=new OrderItem();
        Food food=new Food();
        food.setId("8a5e9d3d64f861290164f8612ea70001");
        orderItem.setFoodNum(1);
        orderItem.setFood(food);
        orderItem.setTotalPrice(666);
        OrderItem orderItem1= manager.addOrderItem(orderItem,"8a5e9d3c64f84c7f0164f84c846c0002");
        Assert.assertTrue(orderItem1!=null);


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
