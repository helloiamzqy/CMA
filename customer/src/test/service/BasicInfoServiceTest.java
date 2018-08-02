package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.BasicInfo;
import pojo.Merchant;
import pojo.Pager;

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
    @Test
    public void findMerchantTestByPager() {
        int curPage=1;
        int pageSize=1;
        BasicInfoService basicInfoService = context.getBean(BasicInfoService.class);
        Pager pager = basicInfoService.findAllBasicInfo(curPage,pageSize);
        List<BasicInfo> basicInfos = (List<BasicInfo>) pager.getList();
        for(BasicInfo b:basicInfos){
            System.out.println(b.getId());
        }
    }
    @Test
    public void findMerchantByIdTest() {
        BasicInfoService basicInfoService = context.getBean(BasicInfoService.class);
       BasicInfo basicInfo =  basicInfoService.findBasicInfoById("8a5e9d3d64f9eed50164f9eedabf0001");
        System.out.println(basicInfo.getShopName());
    }
}
