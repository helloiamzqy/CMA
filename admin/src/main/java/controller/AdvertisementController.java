package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.Advertisement;
import pojo.Page;
import service.AdvertisementManager;

/**
 * @author JohnGao
 * @date 8/2/2018 9:06 AM
 */
@RestController
@RequestMapping(value = "advertisement")
public class AdvertisementController {
    @Autowired
    private AdvertisementManager manager;

    @RequestMapping(method = RequestMethod.PUT,value = "{id},{state}")
    public Advertisement updateAdvertisement(@PathVariable String id,@PathVariable String state){
        return manager.updateAd(id, state);
    }

    @RequestMapping(method = RequestMethod.GET,value = "{currentPage},{pageSize}")
    public Page<Advertisement> getAdvertisement(@PathVariable int currentPage,@PathVariable int pageSize){
        Page<Advertisement> page = manager.getAdsByPage(currentPage,pageSize);
        return page;
    }

    //这个不是添加广告，是提交申请信息，获得可发送的商家名称
    @RequestMapping(method = RequestMethod.POST)
    public String getSendAd(String validation){
        if ("request".equals(validation)){
            return manager.sendAds();
        }
        return "{}";
    }
}
