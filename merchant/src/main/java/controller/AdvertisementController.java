package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pojo.Advertisement;
import service.AdvertisementManager;

import java.util.List;

@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementManager advertisementManager;
    @GetMapping(value = "{mId}/advertisement")
    public List<Advertisement> findAdvertisementByMerchant(@PathVariable(name = "mId") String mId){
        return advertisementManager.findAdvertisementByMerchant(mId);
    }
    @PostMapping(value = "{mId}/advertisement")
    public Advertisement addAdvertisement(@RequestBody Advertisement advertisement, @PathVariable(name = "mId") String mId){
        return advertisementManager.addAdvertisement(advertisement,mId);
    }
    //获取客户端广告.c端
    @GetMapping(value ="advertisement")
    public void getAdvertisements(){

    }
}
