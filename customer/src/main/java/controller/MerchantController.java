package controller;

import org.apache.kahadb.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.BasicInfo;
import pojo.Merchant;
import pojo.Pager;
import service.BasicInfoService;
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
    BasicInfoService basicInfoService;
    @Autowired
    MerchantService merchantService;

    @ResponseBody
    @RequestMapping(value = "/findMerchant")
    public List<Merchant> loginCustomer() {
        return merchantService.findMerchant();
    }


    @ResponseBody
    @RequestMapping(value = "/findBasicInfoByPager/{currentPage}/{pageSize}")
    public Pager findBasicInfo(@PathVariable(name = "currentPage") int currentPage, @PathVariable(name = "pageSize") int pageSize) {
        System.out.println(currentPage+"\t"+pageSize);
        return basicInfoService.findAllBasicInfo(currentPage,pageSize);
    }


    @ResponseBody
    @RequestMapping(value = "/showMerchantDetail")
    public BasicInfo findBasicInfo(String shop_id) {
        System.out.println("shop_id :"+shop_id);
        return basicInfoService.findBasicInfoById(shop_id);
    }
}