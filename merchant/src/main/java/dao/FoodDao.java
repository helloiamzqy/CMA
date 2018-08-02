package dao;

import pojo.Food;
import pojo.Merchant;

import java.util.List;

/**
 * @author Dunn
 */
public interface FoodDao {
     List<Food> findAllFood();
     Food addFood(Food food);
     void deleteFood(String id);
     Food updateFood(Food food);
     List<Food> findFoodByMerchant(Merchant merchant);
     List<Food> findFoodByName(String name);
}
