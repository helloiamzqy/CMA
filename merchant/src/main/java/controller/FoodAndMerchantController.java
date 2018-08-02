package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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



}
