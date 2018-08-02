package manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.MerchantInfo;
import service.MerchantInfoManager;
import service.impl.MerchantInfoManagerImpl;

public class MerchantInfoManagerTest {


    private static ApplicationContext context;
    private MerchantInfoManager manager;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void start(){
        manager = new MerchantInfoManagerImpl();
    }


    @Test
    public void testAddMerchantInfo(){
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setCreditCode("123");
        MerchantInfo merchantInfo1 = manager.addMerchantInfo(merchantInfo);

        Assert.assertTrue(merchantInfo1.getId()!= null);

    }
}
