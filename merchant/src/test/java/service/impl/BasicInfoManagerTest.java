package service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.BasicInfo;
import service.BasicInfoManager;

import java.util.Date;

public class BasicInfoManagerTest {
    private static ApplicationContext context;
    private static BasicInfoManager manager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public  void initManager(){
        manager=context.getBean(BasicInfoManager.class);
    }
    @Test
    public void testAddBasicInfo(){
        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("fdafda");
        BasicInfo basicInfo1= manager.addBasicInfo(basicInfo,"8a5e9d3c64f84c7f0164f84c845c0001");
        Assert.assertTrue(basicInfo1.getMerchant().getId().equals("8a5e9d3c64f84c7f0164f84c845c0001"));

    }

    @Test
    public void testUpdateBasicInfo(){
        BasicInfo basicInfo=new BasicInfo();
        basicInfo.setCloseTime(new Date());
        basicInfo.setOpenTime(new Date());
        basicInfo.setPicture("HELLO");
        basicInfo.setId("8a5e9d3d64f90b6f0164f90b760f0000");
        BasicInfo basicInfo1=manager.updateBasicInfo(basicInfo);
        Assert.assertTrue(basicInfo1.getPicture().equals("HELLO"));
    }
}
