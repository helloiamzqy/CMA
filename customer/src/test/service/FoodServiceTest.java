package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.BasicInfo;
import pojo.Food;
import pojo.Pager;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 3:09 PM 8/2/2018
 * @modified By:
 */
public class FoodServiceTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }
    @Test
    public void findFoodTest() {
        FoodSerivce foodSerivce = context.getBean(FoodSerivce.class);
        List<Food> foods = foodSerivce.findFoodByMerchantId("8a5e9d3d64f9eed50164f9eedabf0001");
        for(Food food:foods){
            System.out.println(food.getFoodName());
        }
    }

}
