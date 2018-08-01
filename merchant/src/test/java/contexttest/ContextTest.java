package contexttest;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;

/**
 * @author Dunn
 */
public class ContextTest {
    private static ApplicationContext context;
    private static EntityManager manager;
    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        manager = context.getId("entityManagerFactory");
    }

}
