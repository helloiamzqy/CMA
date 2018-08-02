package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Customer;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;
import service.CustomerService;
import service.FoodSerivce;
import service.MerchantService;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 7:17 PM 8/2/2018
 * @modified By:
 */

@Controller
@RequestMapping(value = "/food")
public class FoodController {

    @Autowired
    FoodSerivce foodService;
    @Autowired
    MerchantService merchantService;

    @ResponseBody
    @RequestMapping(value = "/findFoodByMerchant/{currentPage}/{pageSize}")
    public Pager findFoodByMerchant(@PathVariable(value = "currentPage")int currentPage, @PathVariable(value = "pageSize")int pageSize, @RequestBody Merchant merchant) {
        System.out.println(merchant);
        System.out.println(currentPage+"\t"+pageSize);
        return foodService.findFoodByMerchant(currentPage,pageSize,merchant);
    }
    @ResponseBody
    @RequestMapping(value = "/findFoodByMerchant/{merchant}")
    public List<Food> findFoodByMerchant(@PathVariable(value = "merchant")String merchantId) {
        System.out.println(merchantId);
        Merchant merchant = merchantService.findMerchantById(merchantId);
        return foodService.findFoodByMerchant(merchant);
    }
}
