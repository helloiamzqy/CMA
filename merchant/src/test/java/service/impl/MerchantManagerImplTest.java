package service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Comment;
import pojo.Customer;
import pojo.Merchant;
import pojo.Order;
import service.CommentManager;
import service.CustomerManager;
import service.MerchantManager;
import service.OrderManager;

import java.util.Date;
import java.util.List;

public class MerchantManagerImplTest {
    private static ApplicationContext context;
    private MerchantManager merchantManager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Before
    public void initManager(){
        merchantManager=context.getBean(MerchantManager.class);
    }
    @Test
    public void testAddMerchant(){
        MerchantManager merchantManager=context.getBean(MerchantManager.class);
        Merchant merchant=new Merchant();

        merchant.setName("123");
        merchant.setPassword("12eqr12");
        Merchant merchant1=merchantManager.addMerchant(merchant);
    }
    @Test
    public void testUpdateMerchant(){
        MerchantManager merchantManager=context.getBean(MerchantManager.class);
        Merchant merchant=new Merchant();
        merchant.setId("8a5e9d3d64f82a610164f82a660e0000");
        merchant.setPassword("fjadjfklajdlf");
        merchant.setName("dafad");
        merchantManager.updateMerchant(merchant);
        Assert.assertTrue(merchant.getName().equals("dafad"));

    }
    @Test
    public void testFindMerchant(){
        merchantManager=context.getBean(MerchantManager.class);
        List<Merchant> merchants=merchantManager.findMerchant();
        Assert.assertTrue(merchants.size()==2);
    }
    @Test
    public void testFindMerchantByName(){
//        Merchant merchant=merchantManager.findMerchantByName("1234");
//        System.out.println(merchant.getId());
//        Assert.assertTrue(merchant.getId().equals("8a5e9d3d64f9eed50164f9eedabf0001"));
    }
}
