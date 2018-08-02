package servicetest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Food;
import mananger.FoodManger;
import pojo.Merchant;

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
    @Test
    public void addFood(){
        Food food  = new Food();
        food.setComments("hello");
        food.setFoodName("kfc");
        Merchant merchant = new Merchant();
        merchant.setId("a");
        food.setPicture("picture");
        food.setStatus("1");
        food.setMerchant(merchant);
        foodService.addFood(food);
        assert (food.getFoodName()!=null);
    }
    @Test
    public void findAllFood(){
        List<Food> foods = foodService.findAllFood();
        assert (foods.size()>0);
    }
    @Test
    public void deleteFood(){
        Food food  = new Food();
        food.setComments("hello");
        food.setFoodName("kfc");
        Merchant merchant = new Merchant();
        merchant.setId("a");
        food.setPicture("picture");
        food.setStatus("1");
        food.setMerchant(merchant);
        foodService.addFood(food);
        foodService.deleteFood(food.getId());
    }
    @Test
    public void findFoodByName(){
        addFood();
        List<Food> foods = foodService.findFoodByName("kfc");
        assert (foods.size()>0);
    }
    @Test
    public void updateFood(){
        List<Food> foods = foodService.findFoodByName("kfc");
        Food food = foods.get(0);
        food.setFoodName("mdl");
        Food newFood = foodService.updateFood(food);
        assert (newFood.getFoodName().equals("mdl"));
    }
    @Test
    public void findFoodByMerchant(){
        Merchant merchant = new Merchant();
        merchant.setId("a");
        List<Food> foods = foodService.findFoodByMerchant(merchant);
        assert(foods.size()>0);
    }
}
