package controller;

import jersey.ServerInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.Advertisement;
import pojo.Food;
import pojo.Merchant;
import pojo.Pager;
import service.FoodSerivce;
import service.MerchantService;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 7:17 PM 8/2/2018
 * @modified By:
 */
@RestController
@Controller
@RequestMapping(value = "/ad")
public class AdController {
    @Autowired
    ServerInteraction serverInteraction;
    //@CrossOrigin(origins = (Config.CUSTOMER_URL), maxAge = 1000000)
    @RequestMapping(value="/findAd",method=RequestMethod.GET)
    public List<Advertisement> findAd() {
        System.out.println("findAd");
        serverInteraction.interact("",null);
        return null;
    }

}
