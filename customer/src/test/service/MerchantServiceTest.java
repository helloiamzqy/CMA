package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Customer;
import pojo.Merchant;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 3:09 PM 8/2/2018
 * @modified By:
 */
public class MerchantServiceTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void findMerchantTest() {
        MerchantService merchantService = context.getBean(MerchantService.class);
        List<Merchant> merchants = merchantService.findMerchant();
        for(Merchant m:merchants){
            System.out.println(m.getName());
        }
    }
}
