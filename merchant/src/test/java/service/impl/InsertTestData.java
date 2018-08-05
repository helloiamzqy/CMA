package service.impl;

import org.aspectj.weaver.ast.Or;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.*;
import service.*;
import java.util.*;

/**
 * @author Dunn
 */
public class InsertTestData {
    private static  AdvertisementManager advertisementManager;
    private static  ApplicationContext context ;
    private static OrderManager orderManager;
    private static OrderItemManager orderItemManager;
    private static CommentManager commentManager;
    private static BasicInfoManager basicInfoManager;
    private static RegistInfoManger registInfoManger;
    private static FoodManger foodManger;
    private static MerchantManager merchantManager;
    private static CustomerManager customerManager;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        merchantManager = context.getBean(MerchantManager.class);
        orderManager = context.getBean(OrderManager.class);
        orderItemManager =  context.getBean(OrderItemManager.class);
        commentManager = context.getBean(CommentManager.class);
        basicInfoManager = context.getBean(BasicInfoManager.class);
        registInfoManger = context.getBean(RegistInfoManger.class);
        foodManger = context.getBean(FoodManger.class);
        customerManager =context.getBean(CustomerManager.class);
        advertisementManager = context.getBean(AdvertisementManager.class);
    }

    @Test
    public void add(){
        //添加商家
        Merchant merchant = new Merchant();
        merchant.setName("dunn");
        merchant.setPassword("123123");
        merchantManager.addMerchant(merchant);
        //添加商家注册信息
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setMerchant(merchant);
        registerInfo.setIdCard("44152219961106");
        registerInfo.setShopName("KFC");
        registerInfo.setPicture("http://10.222.29.191:9091/picture/download/e1d05ae8-560a-42f9-8d29-f6746fbf382f.jpg");
        registerInfo.setCreditCode("441622271812333");
        registerInfo.setCorporateName("zhengdu");
        registerInfo.setComments("ddddddddddd");
        registerInfo.setAddress("珠海");
        registerInfo.setPhone("13718512887");
        registInfoManger.addRegisterInfo(registerInfo);
        //添加商家基本信息
        //http://10.222.29.191:9091/picture/download/e1d05ae8-560a-42f9-8d29-f6746fbf382f.jpg
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("http://10.222.29.191:9091/picture/download/e1d05ae8-560a-42f9-8d29-f6746fbf382f.jpg");
        basicInfo.setShopName(registerInfo.getShopName());
        basicInfo.setComments("sssssssssss");
        basicInfo.setDeliFee(1.0);
        basicInfo.setDelivery("11");
        basicInfo.setSlogan("开店信息");
        basicInfo.setStatus("1");
        basicInfoManager.addBasicInfo(basicInfo,merchant.getId());
        //添加food信息
        Food food = new Food();
        food.setStatus("1");
        food.setFoodName("汉堡");
        food.setPicture("http://10.222.29.191:9091/picture/download/e1d05ae8-560a-42f9-8d29-f6746fbf382f.jpg");
        food.setComments("food介绍");
        food.setPrice(10);
        foodManger.addFood(merchant.getId(),food);
        //添加广告
        Advertisement ad = new Advertisement();
        ad.setPicture("http://10.222.29.191:9091/picture/download/e1d05ae8-560a-42f9-8d29-f6746fbf382f.jpg");
        ad.setPrice(7000);
        advertisementManager.addAdvertisement(ad,merchant.getId());
        //添加客户
        Customer customer = new Customer();
        customer.setPassword("123123");
        customer.setName("dunnC");
        customerManager.addCustomer(customer);
        //添加订单
        Order order = new Order();
        order.setPhone("13719512996");
        order.setAddress("南方软件园");
        order.setCreateTime(new Date());
        order.setTotalPrice(1111);
        order.setStatus("1");
        orderManager.addOrder(order,merchant.getId(),customer.getId());
        //订单里面的菜品
        OrderItem orderItem = new OrderItem();
        orderItem.setFoodNum(11);
        orderItem.setTotalPrice(11);
        orderItemManager.addOrderItem(orderItem,order.getId(),food.getId());
        //添加评论
        Comment comment = new Comment();
        comment.setContent("真是好吃的爆炸");
        comment.setRank(5);
        comment.setCreateTime(new Date());
        commentManager.addComment(order.getId(),comment);
    }
}
