
import manager.JmsSender;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.JmxTp;
import pojo.enums.JmsTpEnum;

import javax.persistence.EntityManager;





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
        j1.setObject("");
        jmsSender.send("123");
    }
}
