package service.impl;

import pojo.*;
import service.CommentManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CustomerManager;
import service.MerchantManager;
import service.OrderManager;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void testAddComment(){
        Order order = createOrder();
        Comment comment = new Comment();
        comment.setContent("nihao");
        comment.setCreateTime(new Date());
        comment.setRank(1);
        commentManager.addComment(order.getId(),comment);
        assert (comment.getId()!=null);
    }
    @Test
    public void testFindAllComment(){
        Pager pager = commentManager.findAllComment(1,10);
        assert (pager.getList().size()>0);
    }

    @Test
    public void testDeleteComment(){
        Order order = createOrder();
        Comment comment = new Comment();
        comment.setContent("nihao");
        comment.setCreateTime(new Date());
        comment.setRank(1);
        commentManager.addComment(order.getId(),comment);
        commentManager.deleteComment(comment.getId());
    }
    @Test
    public void testFindCommentByOrder(){
        Order order = createOrder();
        Comment comment = new Comment();
        comment.setContent("nihao");
        comment.setCreateTime(new Date());
        comment.setRank(1);
        commentManager.addComment(order.getId(),comment);
        Pager pager = commentManager.findCommentByOrder(1,10,order.getId());
        assert(pager.getList().size()>0);
    }
    @Test
    public void testFindCommentByMerchant(){
        Order order = createOrder();
        Comment comment = new Comment();
        comment.setContent("nihao");
        comment.setCreateTime(new Date());
        comment.setRank(1);
        commentManager.addComment(order.getId(),comment);
        Pager pager = commentManager.findCommentByMerchant(1,10,order.getMerchant().getId());
        assert(pager.getList().size()>0);
    }
    private Order createOrder() {
        CustomerManager customerManager=context.getBean(CustomerManager.class);
        MerchantManager merchantManager=context.getBean(MerchantManager.class);
        Merchant merchant=new Merchant();

        merchant.setName("123");
        merchant.setPassword("12eqr12");

//        merchant.setId("8a5e9d3d64f57fad0164f57fb27b0002");
        Customer customer=new Customer();

        customer.setName("213");
        customer.setPassword("erqwr3");
//        customer.setId("8a5e9d3d64f57fad0164f57fb26b0001");
        customerManager.addCustomer(customer);
        merchantManager.addMerchant(merchant);
        Order order=new Order();
        order.setCustomer(customer);
        order.setMerchant(merchant);
        order.setCreateTime(new Date());
        order.setStatus("1");
        order.setTotalPrice(12.23);
        OrderManager orderManager=context.getBean(OrderManager.class);
        return orderManager.addOrder(order,merchant.getId(),customer.getId());
    }

}


