package manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Advertisement;
import pojo.Page;
import service.AdvertisementManager;
import service.impl.AdvertisementManagerImpl;

import java.util.List;

/**
 * @author JohnGao
 * @date 8/2/2018 2:00 PM
 */
public class AdvertisementManagerTest {
    private static ApplicationContext context;
    private AdvertisementManager manager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Before
    public void start() {
        manager = context.getBean(AdvertisementManagerImpl.class);
    }

    @Test
    public void testAddAd(){
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("2222");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("kfc");
        advertisement.setStatus("1");
        Advertisement ad = manager.addAd(advertisement);
        Assert.assertTrue(ad.getId()!=null);
    }

    @Test
    public void testDeleteAdById() throws Exception{
        int num = manager.getAllAds().size();
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("sdfs123sdf");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("delete");
        advertisement.setStatus("1");
        Advertisement ad = manager.addAd(advertisement);
        manager.deleteAdById(ad.getId());
        int num2 = manager.getAllAds().size();
        Assert.assertTrue(num == num2);
    }

    @Test
    public void testSendAds(){
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("sdfs123sdf");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("sendAd");
        advertisement.setStatus("1");
        manager.addAd(advertisement);
        String ads = manager.sendAds();
        System.out.println(ads);
        Assert.assertTrue(ads!=null);
    }

    @Test
    public void testUpdateAd(){
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("sdfs123sdf");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("updateAd");
        advertisement.setStatus("1");
        Advertisement ad = manager.addAd(advertisement);
        Advertisement advertisement1 = manager.updateAd(ad.getId(),"2");
        Assert.assertTrue(advertisement1.getStatus().equals("2"));
    }

    @Test
    public void testGetAdByPage(){
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("sdfs123sdf");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("page");
        advertisement.setStatus("1");
        Advertisement advertisement2 = new Advertisement();
        advertisement2.setMerchantId("sdfs123sdf");
        advertisement2.setPicture("www.ttt.com");
        advertisement2.setPrice(12.36);
        advertisement2.setMerchantName("page2");
        advertisement2.setStatus("1");
        manager.addAd(advertisement);
        manager.addAd(advertisement2);
        Page<Advertisement> page = manager.getAdsByPage(1,2);
        int m = page.getTotalPage();
        Assert.assertTrue(m>=1);
    }

    @Test
    public void testGetAllAd() throws Exception{
        Advertisement advertisement = new Advertisement();
        advertisement.setMerchantId("sdfs123sdf");
        advertisement.setPicture("www.ttt.com");
        advertisement.setPrice(12.36);
        advertisement.setMerchantName("add");
        advertisement.setStatus("1");
        manager.addAd(advertisement);
        int num = manager.getAllAds().size();
        Assert.assertTrue(num>0);
    }
}
