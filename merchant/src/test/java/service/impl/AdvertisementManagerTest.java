package service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Advertisement;
import service.AdvertisementManager;

import java.util.List;

public class AdvertisementManagerTest {
    private static ApplicationContext context;
    private AdvertisementManager manager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void initManager(){
        manager=context.getBean(AdvertisementManager.class);
    }
    @Test
    public void addAdvertisement(){
        Advertisement advertisement=new Advertisement();
        advertisement.setPicture("dfaf");
        advertisement.setPrice(12312);
        Advertisement advertisement1= manager.addAdvertisement(advertisement,"8a5e9d3d64f9eed50164f9eedabf0001");
        Assert.assertTrue(advertisement1.getMerchant().getId().equals("8a5e9d3d64f9eed50164f9eedabf0001"));

    }

    @Test
    public void findAdvertisementByMerchant(){
        List<Advertisement> advertisements=manager.findAdvertisementByMerchant("8a5e9d3d64f9eed50164f9eedabf0001");
        for (Advertisement advertisement:advertisements){
            System.out.println(advertisement.getId());
        }
        Assert.assertTrue(advertisements.size()==2);
    }
}
