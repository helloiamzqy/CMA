package service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.BasicInfo;
import pojo.Merchant;
import service.BasicInfoManager;
import service.MerchantManager;

import java.util.Date;

public class BasicInfoManagerTest {
    private static ApplicationContext context;
    private static BasicInfoManager manager;
    private static MerchantManager emanager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public  void initManager(){
        emanager = context.getBean(MerchantManager.class);
        manager=context.getBean(BasicInfoManager.class);

    }
    @Test
    public void testAddBasicInfo(){
        Merchant merchant = new Merchant();
        merchant.setPassword("12332");
        merchant.setName("aaaaa");
        emanager.addMerchant(merchant);

        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("fdafda");
        basicInfo.setShopName("dafadf");
        BasicInfo basicInfo1= manager.addBasicInfo(basicInfo,merchant.getId());
        Assert.assertTrue(basicInfo1.getMerchant().getId().equals(merchant.getId()));

    }

    @Test
    public void testUpdateBasicInfo(){
        Merchant merchant = new Merchant();
        merchant.setPassword("12332");
        merchant.setName("aaaaa");
        emanager.addMerchant(merchant);
        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("fdafda");
        basicInfo.setShopName("dafadf");
        manager.addBasicInfo(basicInfo,merchant.getId());
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("HELLO");
        basicInfo.setShopName("HELLO");
        BasicInfo basicInfo1=manager.updateBasicInfo(basicInfo,merchant.getId());
        Assert.assertTrue(basicInfo1.getPicture().equals("HELLO"));
    }
    @Test
    public void testFindBasicInfoByMerchant(){
        Merchant merchant = new Merchant();
        merchant.setPassword("12332");
        merchant.setName("aaaaa");
        emanager.addMerchant(merchant);

        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("fdafda");
        basicInfo.setShopName("dafadf");
        BasicInfo basicInfo1= manager.addBasicInfo(basicInfo,merchant.getId());

        BasicInfo basicInfo2=manager.findBasicInfoByMerchant(merchant.getId());
        Assert.assertTrue(basicInfo2.getMerchant().getId().equals(merchant.getId()));
    }
    @Test
     public void addBasicTen(){
        for (int i =0 ;i <10;i++){
            testAddBasicInfo();
        }
    }
}
