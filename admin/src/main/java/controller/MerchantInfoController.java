package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.MerchantInfo;
import service.MerchantInfoManager;

import java.util.List;

@RestController
@RequestMapping(value="/merchantInfo")
public class MerchantInfoController {

    @Autowired
    private MerchantInfoManager manager;

    //更改商家的审核状态
    @PutMapping(value="/update")
    public MerchantInfo updateMerchantInfo(@RequestBody MerchantInfo merchantInfo){
        return manager.updateMerchantInfo(merchantInfo);
    }


    //按状态查询商家列表
    @GetMapping(value="/status/{status}")
    public List<MerchantInfo> findMechantInfosByStatus(@PathVariable String status){
        return manager.findMechantInfosByStatus(status);
    }

    //按用户ID更改商家的状态为黑名单
    @PutMapping(value = "/updateStatus/{merchantId}")
    public MerchantInfo updateMerchantStatus(@PathVariable String merchantId){
        MerchantInfo merchantInfo = manager.findMechantInfoByMerchantId(merchantId);
        merchantInfo.setStatus("2");
        return  manager.updateMerchantInfo(merchantInfo);

    }



}
