package service.impl;

import service.CommentManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Pager;

/**
 * @author Dunn
 */
public class CommentServiceTest {
    private static CommentManager commentManager ;
    private static ApplicationContext context;
    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        commentManager = context.getBean(CommentManager.class);
    }
    //TODO
    @Test
    public void testFindAllComment(){
        Pager pager = commentManager.findAllComment(1,10);
        assert (pager.getList().size()>0);
    }
}
