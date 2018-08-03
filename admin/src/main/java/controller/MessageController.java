package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.MerchantInfo;
import service.MerchantInfoManager;

/**
 * @author JohnGao,Carrie
 * @date 8/2/2018 4:21 PM
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    private MerchantInfoManager merchantInfoManager;

    @GetMapping(value = "merchantStatus/{id}")
    public String getMerchantStatus(@PathVariable String id){
        MerchantInfo merchantInfo = merchantInfoManager.findMechantInfoByMerchantId(id);
        String status=merchantInfo.getStatus();
        String result="{\"status\":\""+status+"\"}";
        return result;
    }
}
