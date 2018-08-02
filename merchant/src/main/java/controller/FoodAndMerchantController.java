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
    @RequestMapping(method = RequestMethod.POST,value = "/merchant/{id}/food")
    public Food addFood(@PathVariable String id,Food food){
        Merchant merchant = new Merchant();
        merchant.setId(id);
        food.setMerchant(merchant);
        foodManger.addFood(food);
        return food;
    }
    @RequestMapping(method=RequestMethod.DELETE,value = "/merchant/{id}/food")
    public void delete(@PathVariable String id,Food food){
        
    }


}