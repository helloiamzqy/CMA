package service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Advertisement;
import pojo.Merchant;
import service.AdvertisementManager;
import service.MerchantManager;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementManagerTest {
    private static ApplicationContext context;
    private AdvertisementManager manager;
    private MerchantManager merchantManager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void initManager(){
        manager=context.getBean(AdvertisementManager.class);
        merchantManager = context.getBean(MerchantManager.class);
    }
    @Test
    public void addAdvertisement(){
        Merchant merchant = new Merchant();
        merchant.setName("asdsd");
        merchant.setPassword("1231232");
        merchantManager.addMerchant(merchant);
        Advertisement advertisement=new Advertisement();
        advertisement.setPicture("dfaf");
        advertisement.setPrice(12312);
        Advertisement advertisement1= manager.addAdvertisement(advertisement,merchant.getId());
        Assert.assertTrue(advertisement1.getMerchant().getId().equals(merchant.getId()));

    }

    @Test
    public void findAdvertisementByMerchant(){
        Merchant merchant = new Merchant();
        merchant.setName("asdsd");
        merchant.setPassword("1231232");
        merchantManager.addMerchant(merchant);
        Advertisement advertisement=new Advertisement();
        advertisement.setPicture("dfaf");
        advertisement.setPrice(12312);
        Advertisement advertisement1= manager.addAdvertisement(advertisement,merchant.getId());
        List<Advertisement> advertisements=manager.findAdvertisementByMerchant(merchant.getId());
        Assert.assertTrue(advertisements.size()>0);
    }

    @Test
    public void testFindAdvertisementByIds(){
        List<String> ads = new ArrayList<>();
        for(int i=0;i<3;i++) {
            Advertisement advertisement=new Advertisement();
            advertisement.setPicture("dfaf");
            advertisement.setPrice(12312);
            Merchant merchant = new Merchant();
            merchant.setName("a");
            merchant.setPassword("b");
            merchantManager.addMerchant(merchant);
            Advertisement advertisement1 = manager.addAdvertisement(advertisement, merchant.getId());
            ads.add(merchant.getId());
        }
        manager.findAdvertisementById(ads);
    }

}
