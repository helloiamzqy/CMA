package service.impl;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Food;
import pojo.Pager;
import service.FoodManger;
import pojo.Merchant;
import service.MerchantManager;

import java.util.List;

/**
 * @author Dunn
 */
public class FoodServiceTest {
    private static FoodManger foodService;
    private static ApplicationContext context;
    private static MerchantManager merchantManager;

    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        foodService = context.getBean(FoodManger.class);
        merchantManager = context.getBean(MerchantManager.class);
    }

    @Test
    public void addFood() {
        Food food = new Food();
        food.setComments("hello");
        food.setFoodName("kfc");
        Merchant merchant = new Merchant();
        merchant.setName("kfc");
        merchant.setPassword("123");
        merchantManager.addMerchant(merchant);
        food.setPicture("picture");
        food.setStatus("1");
        food.setMerchant(merchant);
        foodService.addFood(food);
        assert (food.getFoodName() != null);
    }

    @Test
    public void findAllFood() {
       Pager pager = foodService.findAllFood(1,10);
        assert (pager.getList().size() > 0);
    }

    @Test
    public void deleteFood() {
        Food food = new Food();
        food.setComments("hello");
        food.setFoodName("kfc");
        Merchant merchant = new Merchant();
        merchant.setName("kfc");
        merchant.setPassword("123");
        merchantManager.addMerchant(merchant);
        food.setPicture("picture");
        food.setStatus("1");
        food.setMerchant(merchant);
        foodService.addFood(food);
        foodService.deleteFood(food.getId());
    }

    @Test
    public void findFoodByName() {
        addFood();
        Pager foods = foodService.findFoodByName(1,10,"kfc");
        assert (foods.getList().size() > 0);
    }

    @Test
    public void updateFood() {
        addFood();
        Pager foods = foodService.findFoodByName(1,10,"kfc");
        Food food = (Food) foods.getList().get(0);
        food.setFoodName("mdl");
        Food newFood = foodService.updateFood(food);
        assert (newFood.getFoodName().equals("mdl"));
    }

    @Test
    public void findFoodByMerchant() {
        Merchant merchant = new Merchant();
        merchant.setName("kfc");
        merchant.setPassword("123");
        merchantManager.addMerchant(merchant);
        Food food = new Food();
        food.setFoodName("asdsdd");
        food.setPicture("picture");
        food.setStatus("1");
        food.setMerchant(merchant);
        foodService.addFood(food);
       Pager foods = foodService.findFoodByMerchant(1,10,merchant);
        assert (foods.getList().size() > 0);
    }
}
