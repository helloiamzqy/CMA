package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Advertisement;
import service.AdvertisementManager;

import java.util.List;

@RestController
@RequestMapping("advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementManager advertisementManager;
    @GetMapping(value = "{mId}")
    public List<Advertisement> findAdvertisementByMerchant(@PathVariable(name = "mId") String mId){
        return advertisementManager.findAdvertisementByMerchant(mId);
    }
    @PostMapping(value = "{mId}")
    public Advertisement addAdvertisement(@RequestBody Advertisement advertisement, @PathVariable(name = "mId") String mId){
        return advertisementManager.addAdvertisement(advertisement,mId);
    }

}
