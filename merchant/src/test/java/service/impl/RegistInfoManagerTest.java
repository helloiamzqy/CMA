package service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Merchant;
import pojo.RegisterInfo;
import service.FoodManger;
import service.MerchantManager;
import service.RegistInfoManger;

/**
 * @author Dunn
 */
public class RegistInfoManagerTest {
    private static RegistInfoManger registInfoManger;
    private static ApplicationContext context;
    private static MerchantManager merchantManager;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        registInfoManger = context.getBean(RegistInfoManger.class);
        merchantManager = context.getBean(MerchantManager.class);
    }
    /*
     * RegisterInfo getRegisterInfo(Merchant Merchant);
     * RegisterInfo addRegisterInfo(RegisterInfo registerInfo);
     */
    @Test
    public void testAddRegisterInfo(){
        Merchant merchant = new Merchant();
        merchant.setName("asdd");
        merchant.setPassword("123123");
        merchantManager.addMerchant(merchant);
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setAddress("南方软件园");
        registerInfo.setComments("评论");
        registerInfo.setCorporateName("dunn");
        registerInfo.setCreditCode("441522");
        registerInfo.setPhone("123123123");
        registerInfo.setPicture("asdddddd");
        registerInfo.setShopName("kfc");
        registerInfo.setIdCard("asddddddddd");
        registerInfo.setMerchant(merchant);
        registInfoManger.addRegisterInfo(registerInfo);
        assert (registerInfo.getId()!=null);
    }
    @Test
    public void testGetRegisterInfo(){
        Merchant merchant = new Merchant();
        merchant.setName("asdd");
        merchant.setPassword("123123");
        merchantManager.addMerchant(merchant);
        RegisterInfo registerInfo = new RegisterInfo();
        registerInfo.setAddress("南方软件园");
        registerInfo.setComments("评论");
        registerInfo.setCorporateName("dunn");
        registerInfo.setCreditCode("441522");
        registerInfo.setPhone("123123123");
        registerInfo.setPicture("asdddddd");
        registerInfo.setShopName("kfc");
        registerInfo.setIdCard("asddddddddd");
        registerInfo.setMerchant(merchant);
        registInfoManger.addRegisterInfo(registerInfo);
        RegisterInfo  registerInfos = registInfoManger.getRegisterInfo(merchant);
        assert(registerInfo!=null);
    }

}
