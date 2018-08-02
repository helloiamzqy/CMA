package mananger.impl;

import dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Food;
import pojo.Merchant;
import mananger.FoodManger;

import java.util.List;

/**
 * @author Dunn
 */
@Service
public class FoodMangerImpl implements FoodManger {
    @Autowired
    private FoodDao foodDao;
    @Override
    public List<Food> findAllFood() {
       return foodDao.findAllFood();
    }

    @Override
    @Transactional
    public Food addFood(Food food) {
        return  foodDao.addFood(food);
    }

    @Override
    @Transactional
    public void deleteFood(String id) {
        foodDao.deleteFood(id);
    }

    @Override
    @Transactional
    public Food updateFood(Food food) {
        return  foodDao.updateFood(food);
    }

    @Override
    public List<Food> findFoodByMerchant(Merchant merchant) {
        return foodDao.findFoodByMerchant(merchant);
    }

    @Override
    public List<Food> findFoodByName(String name) {
        return foodDao.findFoodByName(name);
    }
}
