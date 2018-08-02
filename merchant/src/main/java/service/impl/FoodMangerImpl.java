package service.impl;

import dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;
import service.FoodManger;

import java.util.List;

/**
 * @author Dunn
 */
@Service
public class FoodMangerImpl implements FoodManger {
    @Autowired
    private FoodDao foodDao;
    @Override
    public Pager findAllFood(int curPage, int pageSize) {
       return foodDao.findAllFood(curPage,pageSize);
    }

    @Override
    @Transactional
    public Food addFood(String mid,Food food) {
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        food.setMerchant(merchant);
        return  foodDao.addFood(food);
    }

    @Override
    @Transactional
    public void deleteFood(String id) {
        foodDao.deleteFood(id);
    }

    @Override
    @Transactional
    public Food updateFood(String mid,Food food) {
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        food.setMerchant(merchant);
        return  foodDao.updateFood(food);
    }

    @Override
    public Pager findFoodByMerchant(int curPage, int pageSize,Merchant merchant) {
        return foodDao.findFoodByMerchant(curPage,pageSize,merchant);
    }

    @Override
    public Pager findFoodByName(int curPage, int pageSize,String name) {
        return foodDao.findFoodByName(curPage,pageSize,name);
    }
}
