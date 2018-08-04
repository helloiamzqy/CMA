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
@CrossOrigin(origins = "*", maxAge = 3600)
public class FoodController {
    @Autowired
    private FoodManger foodManger;
    @Autowired
    private MerchantManager merchantManager;

    @GetMapping(value = "/food")
    public Pager getAllFood(@RequestParam int curPage, @RequestParam int pageSize){
        return foodManger.findAllFood(curPage,pageSize);

    }
    @PostMapping(value = "/{mid}/food")
    public Food addFood(@PathVariable String mid,@RequestBody Food food){
        foodManger.addFood(mid,food);
        return food;
    }
    @DeleteMapping(value = "/{mid}/food/{fid}")
    public void delete(@PathVariable  String fid){
        foodManger.deleteFood(fid);
    }
    @PutMapping(value = "/{mid}/food")
    public Food updateFood(@PathVariable String mid,@RequestBody Food food){
        foodManger.updateFood(mid,food);
        return food;
    }

    @GetMapping(value = "/food/{foodName}")
    public Pager findFoodByFoodName(int curPage,int pageSize,@PathVariable String foodName){
        return foodManger.findFoodByName(curPage, pageSize, foodName);
    }
    @GetMapping(value = "/{mid}/food")
    public Pager findFoodByMerchant(int curPage,int pageSize,@PathVariable String mid){
        Merchant merchant = new Merchant();
        merchant.setId(mid);
        return foodManger.findFoodByMerchant(curPage, pageSize, merchant);
    }
}
