package service.impl;

import dao.FoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;
import service.FoodSerivce;

import java.util.List;

/**
 * @author Dunn
 */
@Service
public class FoodServiceImpl implements FoodSerivce {
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
    @Transactional
    @Override
    public List<Food> findFoodByMerchant(Merchant merchant) {
        return foodDao.findFoodByMerchant(merchant);
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

    @Override
    public List<Food> findFoodByMerchantId(String merchantId) {
        return foodDao.findFoodByMerchantId(merchantId);
    }

    @Override
    public Food findFoodByFoodId(String id) {
        return foodDao.findFoodByFoodId(id);
    }
}
