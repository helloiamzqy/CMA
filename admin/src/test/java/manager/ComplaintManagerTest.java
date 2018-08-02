package manager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Complaint;
import pojo.Page;
import service.ComplaintManager;
import service.impl.AdvertisementManagerImpl;
import service.impl.ComplaintManagerImpl;

import java.util.Date;

/**
 * @author JohnGao
 * @date 8/2/2018 4:27 PM
 */
public class ComplaintManagerTest {
    private static ApplicationContext context;
    private ComplaintManager manager;
    @BeforeClass
    public static void init() {
        context=new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    @Before
    public void start() {
        manager = context.getBean(ComplaintManagerImpl.class);
    }

    @Test
    public void testAddComplaint(){
        Complaint comp = new Complaint();
        comp.setMerchantId("sdfs123sdf");
        comp.setOrderId("10001ad225");
        comp.setCreateTime(new Date());
        comp.setReason("很难啊吃");
        Complaint complaint = manager.addComplaint(comp);
        Assert.assertTrue(complaint.getId()!=null);
    }

    @Test
    public void testGetAllComplaint() throws Exception{
        Complaint comp = new Complaint();
        comp.setMerchantId("sdfs123sdf");
        comp.setOrderId("10001ad225");
        comp.setCreateTime(new Date());
        comp.setReason("获取");
        manager.addComplaint(comp);
        int num = manager.getAllComplaints().size();
        Assert.assertTrue(num>0);
    }
    @Test
    public void testDeleteAdById() throws Exception{
        int num = manager.getAllComplaints().size();
        Complaint comp = new Complaint();
        comp.setMerchantId("sdfs123sdf");
        comp.setOrderId("10001ad225");
        comp.setCreateTime(new Date());
        comp.setReason("删除");
        Complaint complaint = manager.addComplaint(comp);
        manager.deleteComplaint(complaint.getId());
        int num2 = manager.getAllComplaints().size();
        Assert.assertTrue(num == num2);
    }

    @Test
    public void testGetAdByPage(){
        Complaint comp = new Complaint();
        comp.setMerchantId("sdfs123sdf");
        comp.setOrderId("10001ad225");
        comp.setCreateTime(new Date());
        comp.setReason("page1");
        Complaint comp2 = new Complaint();
        comp2.setMerchantId("sdfs123sdf");
        comp2.setOrderId("10001ad225");
        comp2.setCreateTime(new Date());
        comp2.setReason("page2");
        manager.addComplaint(comp);
        manager.addComplaint(comp2);
        Page<Complaint> page = manager.getComplaintByPage(1,2);
        int m = page.getTotalPage();
        Assert.assertTrue(m>=1);
    }
}
