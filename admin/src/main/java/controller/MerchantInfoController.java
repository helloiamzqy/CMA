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
    @PutMapping(value="/updateStatus")
    public MerchantInfo updateMerchantInfo(@RequestBody MerchantInfo merchantInfo){
        return manager.updateMerchantInfo(merchantInfo);
    }


    //按状态查询商家列表
    @GetMapping(value="/status/{status}")
    public List<MerchantInfo> findMechantInfosByStatus(@PathVariable String status){
        return manager.findMechantInfosByStatus(status);
    }



}
