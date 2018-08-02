
import com.google.gson.Gson;
import manager.JmsSender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Complaint;
import pojo.JmxTp;
import pojo.enums.JmsTpEnum;

import javax.persistence.EntityManager;
import java.util.Date;


public class JmsTest {
    private static ApplicationContext context;
    private static EntityManager manager;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    @Test
    public void testJms() {
        JmsSender jmsSender = context.getBean(JmsSender.class);
        JmxTp j1 = new JmxTp();
        j1.setJmxTpEnum(JmsTpEnum.COMPLAIN);
        Complaint complaint = new Complaint();
//        complaint.setId("123");
        complaint.setCreateTime(new Date());
        complaint.setMerchantId("merchantId");
        complaint.setOrderId("orderId");
        complaint.setReason("难吃有毒！");
        j1.setObject(complaint);
        Gson gson = new Gson();
        String object = gson.toJson(j1);
        System.out.println(object);
        jmsSender.send(object);
    }
}
