package service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Merchant;
import service.MerchantManager;

import java.util.List;

public class MerchantManagerImplTest {
    private static ApplicationContext context;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
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
        merchant.setId("8a5e9d3d64f61d1c0164f61d22250000");
        merchant.setPassword("fjadjfklajdlf");
        merchant.setName("dafad");
        merchantManager.updateMerchant(merchant);
        Assert.assertTrue(merchant.getName().equals("dafad"));

    }
    @Test
    public void testFindMerchant(){
        MerchantManager     merchantManager=context.getBean(MerchantManager.class);
        List<Merchant> merchants=merchantManager.findMerchant();
        Assert.assertTrue(merchants.size()==1);
    }
}
