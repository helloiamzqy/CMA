package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.BasicInfo;
import pojo.Merchant;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 3:09 PM 8/2/2018
 * @modified By:
 */
public class BasicInfoServiceTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void findMerchantTest() {
        BasicInfoService basicInfoService = context.getBean(BasicInfoService.class);
        List<BasicInfo> basicInfos = basicInfoService.findAllBasicInfo();
        for(BasicInfo b:basicInfos){
            System.out.println(b.getId());
        }
    }
}
