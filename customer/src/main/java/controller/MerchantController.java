package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Merchant;
import service.MerchantService;

import java.util.List;

/**
 * @author:HUGO
 * @description:
 * @date:Create in 3:07 PM 8/2/2018
 * @modified By:
 */
@Controller
@RequestMapping(value = "/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
//    @Autowired
//    BasicInfoService BasicInfoService;
    @ResponseBody
    @RequestMapping(value = "/findMerchant")
    public List<Merchant> loginCustomer() {
        return merchantService.findMerchant();
    }

}