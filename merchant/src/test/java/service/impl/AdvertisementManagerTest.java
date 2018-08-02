package service.impl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Advertisement;
import service.AdvertisementManager;

import java.util.List;

public class AdvertisementManagerTest {
    private static ApplicationContext context;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void addAdvertisement(){
        AdvertisementManager manager=context.getBean(AdvertisementManager.class);
        Advertisement advertisement=new Advertisement();
        advertisement.setPicture("dfaf");
        advertisement.setPrice(12312);
        Advertisement advertisement1= manager.addAdvertisement(advertisement,"8a5e9d3c64f84c7f0164f84c845c0001");
        Assert.assertTrue(advertisement1.getMerchant().getId().equals("8a5e9d3c64f84c7f0164f84c845c0001"));

    }

    @Test
    public void findAdvertisementByMerchant(){
        AdvertisementManager manager=context.getBean(AdvertisementManager.class);
        List<Advertisement> advertisements=manager.findAdvertisementByMerchant("8a5e9d3c64f84c7f0164f84c845c0001");
        for (Advertisement advertisement:advertisements){
            System.out.println(advertisement.getId());
        }
        Assert.assertTrue(advertisements.size()==2);
    }
}
