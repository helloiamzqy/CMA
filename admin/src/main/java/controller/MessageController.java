package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Advertisement;
import pojo.Complaint;
import pojo.MerchantInfo;
import service.AdvertisementManager;
import service.ComplaintManager;
import service.MerchantInfoManager;

import java.util.List;

/**
 * @author JohnGao,Carrie
 * @date 8/2/2018 4:21 PM
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    private MerchantInfoManager merchantInfoManager;
    @Autowired
    private AdvertisementManager advertiseManager;
    @Autowired
    private ComplaintManager complaintManager;

    @GetMapping(value = "merchantStatus/{id}")
    public String getMerchantStatus(@PathVariable String id){
        MerchantInfo merchantInfo = merchantInfoManager.findMechantInfoByMerchantId(id);
        String status=merchantInfo.getStatus();
        String result="{\"status\":\""+status+"\"}";
        return result;
    }

//    @RequestMapping(value = "advertisement",method = RequestMethod.GET)
//    public String getSendAd(@RequestParam String validation){
//        if ("request".equals(validation)){
//            return manager.sendAds();
//        }
//        return "{}";
//    }

    @RequestMapping(value = "advertisement",method = RequestMethod.GET)
    public List<Advertisement> getSendAd(){
        return advertiseManager.sendAds();
    }

    @RequestMapping(value ="complaint/{id}",method = RequestMethod.GET)
    public List<Complaint> getComplaint(@PathVariable String id){
        return complaintManager.getComplaintById(id);
    }
}
