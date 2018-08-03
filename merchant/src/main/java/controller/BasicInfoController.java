package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.BasicInfo;
import service.BasicInfoManager;

@RestController
public class BasicInfoController {
    @Autowired
    private BasicInfoManager basicInfoManager;


    @PostMapping(value = "{mid}/basicInfo")
    public BasicInfo addBasicInfo(@PathVariable String mid, @RequestBody BasicInfo basicInfo){
        basicInfoManager.addBasicInfo(basicInfo,mid);
        return basicInfo;
    }
    @PutMapping(value = "{mid}/basicInfo")
    public BasicInfo updateBasicInfo(@PathVariable String mid, @RequestBody BasicInfo basicInfo){
        basicInfoManager.updateBasicInfo(basicInfo,mid);
        return basicInfo;
    }
    @GetMapping(value = "{mid}/basicInfo")
    public BasicInfo findBasicInfoByMerchant(@PathVariable  String mid){
        return basicInfoManager.findBasicInfoByMerchant(mid);
    }
}
