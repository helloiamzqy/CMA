package manager;

import factory.PojoFactory;
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
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        Advertisement ad = manager.addAd(advertisement);
        Assert.assertTrue(ad.getId()!=null);
    }

    @Test
    public void testDeleteAdById() throws Exception{
        int num = manager.getAllAds().size();
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        Advertisement ad = manager.addAd(advertisement);
        manager.deleteAdById(ad.getId());
        int num2 = manager.getAllAds().size();
        Assert.assertTrue(num == num2);
    }


    @Test
    public void testSendAds(){
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        manager.addAd(advertisement);
        List<Advertisement> ads = manager.sendAds();
        Assert.assertTrue(ads!=null);
    }

    @Test
    public void testUpdateAd(){
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        Advertisement ad = manager.addAd(advertisement);
        Advertisement advertisement1 = manager.updateAd(ad.getId(),"2");
        Assert.assertTrue(advertisement1.getStatus().equals("2"));
    }

    @Test
    public void testGetAdByPage(){
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        Advertisement advertisement2 = PojoFactory.getAdvertisementInstance();
        manager.addAd(advertisement);
        manager.addAd(advertisement2);
        Page<Advertisement> page = manager.getAdsByPage(1,2);
        int m = page.getTotalPage();
        Assert.assertTrue(m>=1);
    }

    @Test
    public void testGetAllAd() throws Exception{
        Advertisement advertisement = PojoFactory.getAdvertisementInstance();
        manager.addAd(advertisement);
        int num = manager.getAllAds().size();
        Assert.assertTrue(num>0);
    }
}
