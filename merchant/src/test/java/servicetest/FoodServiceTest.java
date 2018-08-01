package servicetest;

import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Food;
import mananger.FoodManger;

import java.util.List;

/**
 * @author Dunn
 */
public class FoodServiceTest {
    private static FoodManger foodService ;
    private static ApplicationContext context;
    @BeforeClass
    public static void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        foodService = context.getBean(FoodManger.class);
    }

    public void addFood(){
        Food food  = new Food();
        food.setComments("");
    }

    public void findAllFood(){
        List<Food> foods = foodService.findAllFood();
        assert (foods.size()>0);
    }
}
