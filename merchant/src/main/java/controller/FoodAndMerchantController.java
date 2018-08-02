package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;
import service.FoodManger;
import service.MerchantManager;

import java.sql.SQLOutput;

/**
 * @author Dunn
 */
@RestController
public class FoodAndMerchantController {
    @Autowired
    private FoodManger foodManger;
    @Autowired
    private MerchantManager merchantManager;

    @RequestMapping(method = RequestMethod.GET,value = "/food")
    public Pager getAllFood(@RequestParam int curPage, @RequestParam int pageSize){
        return foodManger.findAllFood(curPage,pageSize);

    }
    @RequestMapping(method = RequestMethod.POST,value = "/merchant/{mid}/food")
    public Food addFood(@PathVariable String mid,Food food){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        food.setMerchant(merchant);
        foodManger.addFood(food);
        return food;
    }
    @RequestMapping(method=RequestMethod.DELETE,value = "/merchant/food")
    public void delete(String fid){
        foodManger.deleteFood(fid);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/merchant/{mid}/food")
    public Food updateFood(@PathVariable String mid,Food food){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        food.setMerchant(merchant);
        foodManger.updateFood(food);
        return food;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/food/{foodName}")
    public Pager findFoodByFoodName(int curPage,int pageSize,@PathVariable String foodName){
        return foodManger.findFoodByName(curPage, pageSize, foodName);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/merchant/{mid}/food")
    public Pager findFoodByMerchant(int curPage,int pageSize,@PathVariable String mid){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        return foodManger.findFoodByMerchant(curPage, pageSize, merchant);
    }
}
