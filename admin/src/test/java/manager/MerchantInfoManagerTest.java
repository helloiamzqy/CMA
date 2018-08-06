package manager;

import factory.PojoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.MerchantInfo;
import service.MerchantInfoManager;
import service.impl.MerchantInfoManagerImpl;

import java.util.List;

public class MerchantInfoManagerTest {


    private static ApplicationContext context;
    private MerchantInfoManager manager;

    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void start(){
        manager = context.getBean(MerchantInfoManager.class);
    }


    @Test
    public void testAddMerchantInfo(){
        MerchantInfo merchantInfo;
        MerchantInfo merchantInfo1;
        merchantInfo = PojoFactory.getMerchantInfoInstance();
        merchantInfo1 = manager.addMerchantInfo(merchantInfo);
        Assert.assertTrue(merchantInfo1.getId()!= null);
    }

    @Test
    public void testUpdateMerchantInfo(){
        MerchantInfo merchantInfo;
        MerchantInfo merchantInfo1;
        merchantInfo = PojoFactory.getMerchantInfoInstance();
        merchantInfo1 = manager.addMerchantInfo(merchantInfo);
        merchantInfo1.setStatus("1");
        MerchantInfo merchantInfo2 = manager.updateMerchantInfo(merchantInfo1);
        Assert.assertTrue(merchantInfo2.getStatus().equals("1"));
    }

    @Test
    public void testfindMechantInfosByStatus(){
        MerchantInfo merchantInfo;
        merchantInfo = PojoFactory.getMerchantInfoInstance();
        manager.addMerchantInfo(merchantInfo);
        List<MerchantInfo> list = manager.findMechantInfosByStatus("0");
        Assert.assertTrue(list.size()>0);
    }

    @Test
    public void testMechantInfoByMerchantId(){
        MerchantInfo merchantInfo;
        merchantInfo = PojoFactory.getMerchantInfoInstance();
        manager.addMerchantInfo(merchantInfo);
        MerchantInfo merchantInfo1 = manager.findMechantInfoByMerchantId("76782768724");

        Assert.assertTrue(merchantInfo1 != null);

    }
}
